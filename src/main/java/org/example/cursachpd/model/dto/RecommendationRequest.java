package org.example.cursachpd.model.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * DTO-запрос на получение рекомендаций.
 * Содержит имя пользователя и список категорий для персонализации.
 */
public class RecommendationRequest {

    @NotBlank(message = "Username is required")
    private String username;

    private List<String> categories;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public RecommendationRequest() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param username имя пользователя
     * @param categories список предпочтительных категорий
     */
    public RecommendationRequest(String username, List<String> categories) {
        this.username = username;
        this.categories = categories;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Возвращает список предпочтительных категорий.
     *
     * @return список категорий
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Устанавливает список предпочтительных категорий.
     *
     * @param categories список категорий
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
