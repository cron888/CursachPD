package org.example.cursachpd.model.dto;

import java.util.Map;

/**
 * DTO-ответ со статистикой приложения.
 * Содержит общие метрики: количество рекомендаций, активных правил, пользователей
 * и распределение рекомендаций по категориям.
 */
public class StatisticsResponse {

    private long totalRecommendations;
    private long activeRules;
    private long totalUsers;
    private Map<String, Long> recommendationsByCategory;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public StatisticsResponse() {
    }

    /**
     * Возвращает общее количество сгенерированных рекомендаций.
     *
     * @return общее количество рекомендаций
     */
    public long getTotalRecommendations() {
        return totalRecommendations;
    }

    /**
     * Устанавливает общее количество сгенерированных рекомендаций.
     *
     * @param totalRecommendations общее количество рекомендаций
     */
    public void setTotalRecommendations(long totalRecommendations) {
        this.totalRecommendations = totalRecommendations;
    }

    /**
     * Возвращает количество активных правил.
     *
     * @return количество активных правил
     */
    public long getActiveRules() {
        return activeRules;
    }

    /**
     * Устанавливает количество активных правил.
     *
     * @param activeRules количество активных правил
     */
    public void setActiveRules(long activeRules) {
        this.activeRules = activeRules;
    }

    /**
     * Возвращает общее количество пользователей.
     *
     * @return общее количество пользователей
     */
    public long getTotalUsers() {
        return totalUsers;
    }

    /**
     * Устанавливает общее количество пользователей.
     *
     * @param totalUsers общее количество пользователей
     */
    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    /**
     * Возвращает распределение рекомендаций по категориям.
     *
     * @return карта категория → количество
     */
    public Map<String, Long> getRecommendationsByCategory() {
        return recommendationsByCategory;
    }

    /**
     * Устанавливает распределение рекомендаций по категориям.
     *
     * @param recommendationsByCategory карта категория → количество
     */
    public void setRecommendationsByCategory(Map<String, Long> recommendationsByCategory) {
        this.recommendationsByCategory = recommendationsByCategory;
    }
}
