package org.example.cursachpd.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность пользователя системы.
 * Хранит информацию о пользователе, его предпочтениях по категориям
 * и истории полученных рекомендаций.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "telegram_chat_id")
    private Long telegramChatId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations = new ArrayList<>();

    @Column(name = "preferred_categories")
    private String preferredCategories;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Устанавливает дату создания при сохранении новой сущности.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Возвращает уникальный идентификатор пользователя.
     *
     * @return id пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор пользователя.
     *
     * @param id идентификатор пользователя
     */
    public void setId(Long id) {
        this.id = id;
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
     * Возвращает идентификатор чата Telegram пользователя.
     *
     * @return идентификатор чата Telegram
     */
    public Long getTelegramChatId() {
        return telegramChatId;
    }

    /**
     * Устанавливает идентификатор чата Telegram пользователя.
     *
     * @param telegramChatId идентификатор чата Telegram
     */
    public void setTelegramChatId(Long telegramChatId) {
        this.telegramChatId = telegramChatId;
    }

    /**
     * Возвращает список рекомендаций пользователя.
     *
     * @return список рекомендаций
     */
    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    /**
     * Устанавливает список рекомендаций пользователя.
     *
     * @param recommendations список рекомендаций
     */
    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * Возвращает предпочтительные категории пользователя.
     *
     * @return строка с предпочтительными категориями
     */
    public String getPreferredCategories() {
        return preferredCategories;
    }

    /**
     * Устанавливает предпочтительные категории пользователя.
     *
     * @param preferredCategories строка с предпочтительными категориями
     */
    public void setPreferredCategories(String preferredCategories) {
        this.preferredCategories = preferredCategories;
    }

    /**
     * Возвращает дату создания пользователя.
     *
     * @return дата создания
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания пользователя.
     *
     * @param createdAt дата создания
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
