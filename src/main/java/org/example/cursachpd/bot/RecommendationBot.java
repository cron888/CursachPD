package org.example.cursachpd.bot;

import org.example.cursachpd.repository.UserRepository;
import org.example.cursachpd.service.RecommendationService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Telegram бот для получения рекомендаций (актор User).
 * Обрабатывает команды: /start, /recommendations, /categories.
 * Использует deprecated-методы Telegram Bot API для совместимости.
 */
@SuppressWarnings("deprecation")
public class RecommendationBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;
    private final RecommendationService recommendationService;
    private final UserRepository userRepository;

    /**
     * Конструктор без зависимостей для конфигурации Spring.
     *
     * @param botToken токен бота Telegram
     * @param botUsername имя бота Telegram
     */
    public RecommendationBot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.recommendationService = null;
        this.userRepository = null;
    }

    /**
     * Конструктор с зависимостями для Spring-managed бина.
     *
     * @param botToken токен бота Telegram
     * @param botUsername имя бота Telegram
     * @param recommendationService сервис рекомендаций
     * @param userRepository репозиторий пользователей
     */
    public RecommendationBot(String botToken, String botUsername,
                             RecommendationService recommendationService,
                             UserRepository userRepository) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.recommendationService = recommendationService;
        this.userRepository = userRepository;
    }

    /**
     * Обрабатывает полученные обновления от Telegram.
     * Определяет команду и вызывает соответствующий обработчик.
     *
     * @param update обновление от Telegram
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            switch (text.toLowerCase()) {
                case "/start":
                    handleStart(chatId);
                    break;
                case "/recommendations":
                    handleRecommendations(chatId);
                    break;
                case "/categories":
                    handleCategories(chatId);
                    break;
                default:
                    sendMessage(chatId, "Unknown command. Use /start to see available commands.");
                    break;
            }
        }
    }

    /**
     * Обрабатывает команду /start — показывает приветствие и список команд.
     *
     * @param chatId идентификатор чата
     */
    private void handleStart(Long chatId) {
        String message = "Hello! I'm the Recommendations Bot.\n\n"
                + "Available commands:\n"
                + "/recommendations - Get personalized recommendations\n"
                + "/categories - Set your preferred categories\n"
                + "/help - Show this help message";
        sendMessage(chatId, message);
    }

    /**
     * Обрабатывает команду /recommendations — отправляет ссылку на REST API.
     *
     * @param chatId идентификатор чата
     */
    private void handleRecommendations(Long chatId) {
        sendMessage(chatId, "To get recommendations, please use the REST API at /api/recommendations");
    }

    /**
     * Обрабатывает команду /categories — отправляет ссылку на REST API.
     *
     * @param chatId идентификатор чата
     */
    private void handleCategories(Long chatId) {
        sendMessage(chatId, "To set your categories, please use the REST API at /api/recommendations");
    }

    /**
     * Возвращает имя бота для Telegram API.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    /**
     * Возвращает токен бота для Telegram API.
     *
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * Отправляет текстовое сообщение в чат Telegram.
     *
     * @param chatId идентификатор чата
     * @param text текст сообщения
     */
    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
