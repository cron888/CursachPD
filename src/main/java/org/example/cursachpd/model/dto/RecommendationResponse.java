package org.example.cursachpd.model.dto;

import java.util.List;

/**
 * DTO-ответ с рекомендациями.
 * Содержит список рекомендованных элементов и названия matched правил.
 */
public class RecommendationResponse {

    private List<RecommendationDto> recommendations;
    private List<String> matchedRules;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public RecommendationResponse() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param recommendations список рекомендаций
     * @param matchedRules названия matched правил
     */
    public RecommendationResponse(List<RecommendationDto> recommendations, List<String> matchedRules) {
        this.recommendations = recommendations;
        this.matchedRules = matchedRules;
    }

    /**
     * Возвращает список рекомендаций.
     *
     * @return список рекомендаций
     */
    public List<RecommendationDto> getRecommendations() {
        return recommendations;
    }

    /**
     * Устанавливает список рекомендаций.
     *
     * @param recommendations список рекомендаций
     */
    public void setRecommendations(List<RecommendationDto> recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * Возвращает названия matched правил.
     *
     * @return названия matched правил
     */
    public List<String> getMatchedRules() {
        return matchedRules;
    }

    /**
     * Устанавливает названия matched правил.
     *
     * @param matchedRules названия matched правил
     */
    public void setMatchedRules(List<String> matchedRules) {
        this.matchedRules = matchedRules;
    }
}
