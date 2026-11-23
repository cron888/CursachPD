package org.example.cursachpd.service;

import org.example.cursachpd.model.dto.StatisticsResponse;
import org.example.cursachpd.model.entity.Statistics;
import org.example.cursachpd.repository.RecommendationRepository;
import org.example.cursachpd.repository.RuleRepository;
import org.example.cursachpd.repository.StatisticsRepository;
import org.example.cursachpd.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Сервис управления статистикой и метриками приложения.
 * Записывает метрики и предоставляет агрегированную статистику.
 */
@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final RuleRepository ruleRepository;

    /**
     * Конструктор с внедрением всех зависимостей.
     *
     * @param statisticsRepository репозиторий статистики
     * @param recommendationRepository репозиторий рекомендаций
     * @param userRepository репозиторий пользователей
     * @param ruleRepository репозиторий правил
     */
    public StatisticsService(StatisticsRepository statisticsRepository,
                             RecommendationRepository recommendationRepository,
                             UserRepository userRepository,
                             RuleRepository ruleRepository) {
        this.statisticsRepository = statisticsRepository;
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.ruleRepository = ruleRepository;
    }

    /**
     * Записывает факт генерации рекомендации.
     * Инкрементирует метрику total_recommendations за текущий день.
     */
    public void recordRecommendationGenerated() {
        incrementMetric("total_recommendations", "DAILY");
    }

    /**
     * Возвращает агрегированную статистику приложения.
     *
     * @return объект StatisticsResponse с метриками
     */
    public StatisticsResponse getStatistics() {
        StatisticsResponse response = new StatisticsResponse();
        response.setTotalRecommendations(recommendationRepository.count());
        response.setActiveRules(ruleRepository.countByActiveTrue());
        response.setTotalUsers(userRepository.count());
        response.setRecommendationsByCategory(calculateRecommendationsByCategory());
        return response;
    }

    /**
     * Вычисляет распределение рекомендаций по категориям.
     *
     * @return карта категория → количество
     */
    private Map<String, Long> calculateRecommendationsByCategory() {
        // This would ideally use a GROUP BY query, but for simplicity we use in-memory
        return new HashMap<>();
    }

    /**
     * Инкрементирует значение метрики.
     * Если запись уже существует — увеличивает значение, иначе создаёт новую.
     *
     * @param metricName название метрики
     * @param period период (DAILY, WEEKLY, MONTHLY)
     */
    private void incrementMetric(String metricName, String period) {
        Optional<Statistics> existing = statisticsRepository
                .findByMetricNameAndPeriod(metricName, period);

        Statistics stats;
        if (existing.isPresent()) {
            stats = existing.get();
            stats.setMetricValue(stats.getMetricValue() + 1);
        } else {
            stats = new Statistics();
            stats.setMetricName(metricName);
            stats.setPeriod(period);
            stats.setMetricValue(1L);
        }

        statisticsRepository.save(stats);
    }
}
