package org.example.cursachpd.config;

import org.example.cursachpd.bot.RecommendationBot;
import org.example.cursachpd.repository.UserRepository;
import org.example.cursachpd.service.RecommendationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Telegram бота.
 * Создаёт бин RecommendationBot с токеном и именем из конфигурации.
 */
@Configuration
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    /**
     * Создаёт бин Telegram бота рекомендаций.
     *
     * @param recommendationService сервис рекомендаций
     * @param userRepository репозиторий пользователей
     * @return настроенный RecommendationBot
     */
    @Bean
    public RecommendationBot recommendationBot(RecommendationService recommendationService,
                                                UserRepository userRepository) {
        return new RecommendationBot(botToken, botUsername, recommendationService, userRepository);
    }
}
