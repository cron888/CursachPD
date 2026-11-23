package org.example.cursachpd.engine;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Движок для оценки статических правил.
 * Статические правила встроены в код и не могут быть изменены во время выполнения.
 * Включает: категорийное, временное, частотное и популярное правила.
 */
@Component
public class StaticRuleEngine {

    /**
     * Оценивает категорийное правило.
     * Если пользователь интересуется категорией, рекомендует элементы с соответствующими тегами.
     *
     * @param userCategories список категорий пользователя
     * @return карта с результатами: ruleName и matchedTags
     */
    public Map<String, Object> evaluateCategoryRule(List<String> userCategories) {
        Map<String, Object> result = new LinkedHashMap<>();
        List<String> matchingTags = userCategories.stream()
                .flatMap(cat -> getTagsForCategory(cat).stream())
                .distinct()
                .collect(Collectors.toList());
        result.put("ruleName", "CategoryRule");
        result.put("matchedTags", matchingTags);
        return result;
    }

    /**
     * Оценивает временное правило.
     * Рекомендует разный контент в зависимости от времени суток.
     *
     * @return карта с результатами: ruleName, timeOfDay, recommendationType
     */
    public Map<String, Object> evaluateTimeBasedRule() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        String timeOfDay;
        String recommendationType;

        if (hour >= 6 && hour < 12) {
            timeOfDay = "MORNING";
            recommendationType = "news_and_updates";
        } else if (hour >= 12 && hour < 18) {
            timeOfDay = "AFTERNOON";
            recommendationType = "tutorials_and_guides";
        } else if (hour >= 18 && hour < 22) {
            timeOfDay = "EVENING";
            recommendationType = "interactive_content";
        } else {
            timeOfDay = "NIGHT";
            recommendationType = "review_and_reflection";
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("ruleName", "TimeBasedRule");
        result.put("timeOfDay", timeOfDay);
        result.put("recommendationType", recommendationType);
        return result;
    }

    /**
     * Оценивает частотное правило.
     * Предотвращает повторную рекомендацию одного и того же контента.
     *
     * @param previouslyRecommended список ранее рекомендованных элементов
     * @return карта с результатами: ruleName и blockedTags
     */
    public Map<String, Object> evaluateFrequencyRule(List<String> previouslyRecommended) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> blockedTags = new HashSet<>();
        for (String prev : previouslyRecommended) {
            blockedTags.add(prev);
        }
        result.put("ruleName", "FrequencyRule");
        result.put("blockedTags", blockedTags);
        return result;
    }

    /**
     * Оценивает правило популярности.
     * Включает трендовые и популярные элементы в рекомендации.
     *
     * @return карта с результатами: ruleName и trendingTags
     */
    public Map<String, Object> evaluatePopularityRule() {
        Map<String, Object> result = new LinkedHashMap<>();
        List<String> trendingTags = Arrays.asList("trending", "popular", "featured");
        result.put("ruleName", "PopularityRule");
        result.put("trendingTags", trendingTags);
        return result;
    }

    /**
     * Оценивает все статические правила и объединяет результаты.
     *
     * @param userCategories список категорий пользователя
     * @param previouslyRecommended список ранее рекомендованных элементов
     * @return карта со всеми результатами оценки правил
     */
    public Map<String, Object> evaluateAll(List<String> userCategories,
                                           List<String> previouslyRecommended) {
        Map<String, Object> allResults = new LinkedHashMap<>();

        allResults.put("categoryRule", evaluateCategoryRule(userCategories));
        allResults.put("timeBasedRule", evaluateTimeBasedRule());
        allResults.put("frequencyRule", evaluateFrequencyRule(previouslyRecommended));
        allResults.put("popularityRule", evaluatePopularityRule());

        return allResults;
    }

    /**
     * Возвращает рекомендуемые теги для заданной категории.
     *
     * @param category категория пользователя
     * @return список тегов для категории
     */
    private List<String> getTagsForCategory(String category) {
        return switch (category.toLowerCase()) {
            case "programming", "development" ->
                    Arrays.asList("coding", "tutorials", "projects", "articles");
            case "mathematics", "algebra", "calculus" ->
                    Arrays.asList("problems", "theory", "exercises", "proofs");
            case "data_science", "machine_learning" ->
                    Arrays.asList("datasets", "models", "algorithms", "notebooks");
            case "web_development" ->
                    Arrays.asList("frameworks", "css", "javascript", "api");
            default -> Arrays.asList("general", "educational");
        };
    }
}
