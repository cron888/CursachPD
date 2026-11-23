package org.example.cursachpd.service;

import org.example.cursachpd.engine.RecommendationEngine;
import org.example.cursachpd.model.dto.RecommendationDto;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис управления рекомендациями с паттерном cache-aside.
 * Проверяет кеш перед генерацией новых рекомендаций, сохраняет результат в кеш.
 */
@Service
public class RecommendationService {

    private final RecommendationEngine recommendationEngine;
    private final CacheManager cacheManager;

    /**
     * Конструктор с внедрением зависимостей.
     *
     * @param recommendationEngine движок генерации рекомендаций
     * @param cacheManager менеджер кеша
     */
    public RecommendationService(RecommendationEngine recommendationEngine,
                                 CacheManager cacheManager) {
        this.recommendationEngine = recommendationEngine;
        this.cacheManager = cacheManager;
    }

    /**
     * Получает рекомендации для пользователя с кэшированием.
     * Сначала проверяет кеш, если нет — генерирует и сохраняет в кеш.
     *
     * @param username имя пользователя
     * @param categories список категорий пользователя
     * @return список рекомендаций
     */
    public List<RecommendationDto> getRecommendations(String username, List<String> categories) {
        String cacheKey = buildCacheKey(username, categories);

        var cache = cacheManager.getCache("recommendations");
        if (cache != null) {
            var cachedValue = cache.get(cacheKey);
            if (cachedValue != null) {
                @SuppressWarnings("unchecked")
                List<RecommendationDto> cached = (List<RecommendationDto>) cachedValue.get();
                return cached;
            }
        }

        List<RecommendationDto> recommendations = recommendationEngine.generateRecommendations(
                username, categories);

        if (cache != null) {
            cache.put(cacheKey, recommendations);
        }

        return recommendations;
    }

    /**
     * Очищает кеш рекомендаций для конкретного пользователя.
     *
     * @param username имя пользователя
     */
    public void clearUserCache(String username) {
        var cache = cacheManager.getCache("recommendations");
        if (cache != null) {
            cache.evictIfPresent(buildCacheKey(username, List.of()));
        }
    }

    /**
     * Формирует ключ кеша на основе имени пользователя и категорий.
     *
     * @param username имя пользователя
     * @param categories список категорий
     * @return ключ кеша
     */
    private String buildCacheKey(String username, List<String> categories) {
        String sortedCategories = categories.stream().sorted().collect(Collectors.joining(","));
        return "rec:" + username + ":" + sortedCategories;
    }
}
