package org.example.cursachpd.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность рекомендации.
 * Хранит информацию о сгенерированной рекомендации: её содержание,
 * категорию, источник и связанные с ней правила.
 */
@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "source")
    private String source;

    @Column(name = "matched_rules")
    private String matchedRules;

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
     * Возвращает уникальный идентификатор рекомендации.
     *
     * @return id рекомендации
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор рекомендации.
     *
     * @param id идентификатор рекомендации
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает пользователя, которому предназначена рекомендация.
     *
     * @return пользователь
     */
    public User getUser() {
        return user;
    }

    /**
     * Устанавливает пользователя, которому предназначена рекомендация.
     *
     * @param user пользователь
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Возвращает заголовок рекомендации.
     *
     * @return заголовок
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает заголовок рекомендации.
     *
     * @param title заголовок
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание рекомендации.
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание рекомендации.
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает категорию рекомендации.
     *
     * @return категория
     */
    public String getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию рекомендации.
     *
     * @param category категория
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Возвращает источник рекомендации (REST_API или TELEGRAM_BOT).
     *
     * @return источник
     */
    public String getSource() {
        return source;
    }

    /**
     * Устанавливает источник рекомендации.
     *
     * @param source источник
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Возвращает названия правил, которые сработали при генерации.
     *
     * @return названияmatched правил
     */
    public String getMatchedRules() {
        return matchedRules;
    }

    /**
     * Устанавливает названия правил, которые сработали при генерации.
     *
     * @param matchedRules названия matched правил
     */
    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }

    /**
     * Возвращает дату создания рекомендации.
     *
     * @return дата создания
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания рекомендации.
     *
     * @param createdAt дата создания
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
