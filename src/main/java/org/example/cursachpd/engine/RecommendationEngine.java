package org.example.cursachpd.engine;

import org.example.cursachpd.model.dto.RecommendationDto;
import org.example.cursachpd.model.entity.Recommendation;
import org.example.cursachpd.model.entity.Rule;
import org.example.cursachpd.model.entity.User;
import org.example.cursachpd.repository.RecommendationRepository;
import org.example.cursachpd.repository.UserRepository;
import org.example.cursachpd.service.StatisticsService;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Главный движок рекомендаций.
 * Оркеструет оценку статических и динамических правил,
 * генерирует персонализированные рекомендации для пользователей.
 */
@Component
public class RecommendationEngine {

    private final StaticRuleEngine staticRuleEngine;
    private final DynamicRuleEngine dynamicRuleEngine;
    private final UserRepository userRepository;
    private final RecommendationRepository recommendationRepository;
    private final StatisticsService statisticsService;

    /**
     * Конструктор с внедрением всех зависимостей.
     *
     * @param staticRuleEngine движок статических правил
     * @param dynamicRuleEngine движок динамических правил
     * @param userRepository репозиторий пользователей
     * @param recommendationRepository репозиторий рекомендаций
     * @param statisticsService сервис статистики
     */
    public RecommendationEngine(StaticRuleEngine staticRuleEngine,
                                DynamicRuleEngine dynamicRuleEngine,
                                UserRepository userRepository,
                                RecommendationRepository recommendationRepository,
                                StatisticsService statisticsService) {
        this.staticRuleEngine = staticRuleEngine;
        this.dynamicRuleEngine = dynamicRuleEngine;
        this.userRepository = userRepository;
        this.recommendationRepository = recommendationRepository;
        this.statisticsService = statisticsService;
    }

    /**
     * Генерирует рекомендации для пользователя на основе его категорий.
     * Объединяет оценку статических и динамических правил.
     *
     * @param username имя пользователя
     * @param categories список категорий пользователя
     * @return список DTO рекомендаций
     */
    public List<RecommendationDto> generateRecommendations(String username, List<String> categories) {
        // Get or create user
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> createNewUser(username, categories));

        // Get previously recommended items to avoid repetition
        List<Recommendation> previousRecommendations =
                recommendationRepository.findByUserOrderByCreatedAtDesc(user);
        List<String> previouslyRecommended = previousRecommendations.stream()
                .map(Recommendation::getCategory)
                .limit(10)
                .collect(Collectors.toList());

        // Evaluate static rules
        Map<String, Object> staticResults = staticRuleEngine.evaluateAll(categories, previouslyRecommended);

        // Evaluate dynamic rules
        List<Rule> matchedDynamicRules = dynamicRuleEngine.evaluateRules(categories);

        // Generate recommendations based on rule results
        List<Recommendation> recommendations = buildRecommendations(
                user, categories, previouslyRecommended, staticResults, matchedDynamicRules);

        // Save recommendations
        recommendationRepository.saveAll(recommendations);

        // Update statistics
        statisticsService.recordRecommendationGenerated();

        // Convert to DTOs
        return recommendations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Создаёт нового пользователя с заданными категориями.
     *
     * @param username имя пользователя
     * @param categories список категорий пользователя
     * @return сохранённый пользователь
     */
    private User createNewUser(String username, List<String> categories) {
        User user = new User();
        user.setUsername(username);
        user.setPreferredCategories(String.join(",", categories));
        return userRepository.save(user);
    }

    /**
     * Формирует список рекомендаций на основе результатов оценки правил.
     * Алгоритм: собирает теги из статических правил, фильтрует по частоте,
     * добавляет теги из динамических правил, создаёт объекты рекомендаций.
     *
     * @param user пользователь
     * @param categories категории пользователя
     * @param previouslyRecommended ранее рекомендованные элементы
     * @param staticResults результаты оценки статических правил
     * @param matchedDynamicRules matched динамические правила
     * @return список сгенерированных рекомендаций
     */
    @SuppressWarnings("unchecked")
    private List<Recommendation> buildRecommendations(User user,
                                                      List<String> categories,
                                                      List<String> previouslyRecommended,
                                                      Map<String, Object> staticResults,
                                                      List<Rule> matchedDynamicRules) {
        List<Recommendation> recommendations = new ArrayList<>();

        // Collect all recommended tags from static rules
        Set<String> recommendedTags = new LinkedHashSet<>();

        // Category rule tags
        Map<String, Object> categoryRule = (Map<String, Object>) staticResults.get("categoryRule");
        if (categoryRule != null) {
            List<String> tags = (List<String>) categoryRule.get("matchedTags");
            if (tags != null) {
                recommendedTags.addAll(tags);
            }
        }

        // Time-based rule
        Map<String, Object> timeRule = (Map<String, Object>) staticResults.get("timeBasedRule");
        if (timeRule != null) {
            recommendedTags.add((String) timeRule.get("recommendationType"));
        }

        // Popularity rule
        Map<String, Object> popularityRule = (Map<String, Object>) staticResults.get("popularityRule");
        if (popularityRule != null) {
            List<String> trending = (List<String>) popularityRule.get("trendingTags");
            if (trending != null) {
                recommendedTags.addAll(trending);
            }
        }

        // Frequency rule - blocked tags
        Map<String, Object> frequencyRule = (Map<String, Object>) staticResults.get("frequencyRule");
        if (frequencyRule != null) {
            Set<String> blocked = (Set<String>) frequencyRule.get("blockedTags");
            if (blocked != null) {
                recommendedTags.removeAll(blocked);
            }
        }

        // Add tags from matched dynamic rules
        List<String> matchedRuleNames = new ArrayList<>();
        for (Rule rule : matchedDynamicRules) {
            matchedRuleNames.add(rule.getName());
            String action = rule.getAction();
            String tags = extractValue(action, "recommendation_tags");
            if (tags != null) {
                String[] tagArray = tags.replaceAll("[\\[\\]\"]", "").split(",");
                for (String tag : tagArray) {
                    recommendedTags.add(tag.trim());
                }
            }
        }

        // Build recommendation objects from tags
        int counter = 1;
        for (String tag : recommendedTags) {
            if (recommendations.size() >= 10) {
                break;
            }

            Recommendation rec = new Recommendation();
            rec.setUser(user);
            rec.setTitle("Recommendation #" + counter + ": " + capitalize(tag));
            rec.setDescription("Based on your interest in " + String.join(", ", categories)
                    + " - suggested topic: " + tag);
            rec.setCategory(tag);
            rec.setSource("REST_API");
            rec.setMatchedRules(String.join(",", matchedRuleNames));
            recommendations.add(rec);
            counter++;
        }

        return recommendations;
    }

    /**
     * Преобразует сущность Recommendation в DTO.
     *
     * @param recommendation сущность рекомендации
     * @return DTO рекомендации
     */
    private RecommendationDto toDto(Recommendation recommendation) {
        return new RecommendationDto(
                recommendation.getTitle(),
                recommendation.getDescription(),
                recommendation.getCategory(),
                recommendation.getSource()
        );
    }

    /**
     * Простой парсер JSON для извлечения значения по ключу.
     *
     * @param json JSON-строка
     * @param key ключ для поиска
     * @return значение ключа или null
     */
    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = json.indexOf(':', keyIndex) + 1;
        while (valueStart < json.length() && json.charAt(valueStart) == ' ') {
            valueStart++;
        }

        if (valueStart >= json.length()) {
            return null;
        }

        char startChar = json.charAt(valueStart);
        if (startChar == '"') {
            int endQuote = json.indexOf('"', valueStart + 1);
            return json.substring(valueStart + 1, endQuote);
        } else if (startChar == '[') {
            int endBracket = json.indexOf(']', valueStart);
            return json.substring(valueStart, endBracket + 1);
        }

        return null;
    }

    /**
     * Приводит первую букву строки к верхнему регистру.
     *
     * @param str входная строка
     * @return строка с заглавной первой буквой
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
