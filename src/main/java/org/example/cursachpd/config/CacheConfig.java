package org.example.cursachpd.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Конфигурация менеджера кеша.
 * Настраивает CaffeineCacheManager с TTL 30 минут и максимальным размером 1000 элементов.
 */
@Configuration
public class CacheConfig {

    /**
     * Создаёт и настраивает менеджер кеша Caffeine.
     *
     * @return настроенный CaffeineCacheManager
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("recommendations");
        manager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(1000));
        return manager;
    }
}
