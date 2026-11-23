package org.example.cursachpd.service;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * Сервис управления кешем.
 * Предоставляет операции get/put/clear для кеша рекомендаций.
 * Используется как приложением, так и внешними системами.
 */
@Service
public class CacheService {

    private final CacheManager cacheManager;

    /**
     * Конструктор с внедрением зависимости CacheManager.
     *
     * @param cacheManager менеджер кеша
     */
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Возвращает значение из кеша по ключу.
     *
     * @param key ключ кеша
     * @param type тип возвращаемого значения
     * @param <T> тип значения
     * @return значение из кеша или null если не найдено
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        var cache = cacheManager.getCache("recommendations");
        if (cache != null) {
            var value = cache.get(key);
            return value != null ? (T) value.get() : null;
        }
        return null;
    }

    /**
     * Сохраняет значение в кеш по ключу.
     *
     * @param key ключ кеша
     * @param value значение для сохранения
     * @param <T> тип значения
     */
    public <T> void put(String key, T value) {
        var cache = cacheManager.getCache("recommendations");
        if (cache != null) {
            cache.put(key, value);
        }
    }

    /**
     * Очищает все данные в кеше рекомендаций.
     * Используется внешними системами для инвалидации кеша.
     */
    public void clearAll() {
        var cache = cacheManager.getCache("recommendations");
        if (cache != null) {
            cache.clear();
        }
    }
}
