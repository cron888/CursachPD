package org.example.cursachpd.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность правила рекомендаций.
 * Хранит динамические и статические правила, определяющие логику
 * формирования рекомендаций для пользователей.
 */
@Entity
@Table(name = "rules")
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String condition;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String action;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "rule_type")
    @Enumerated(EnumType.STRING)
    private RuleType ruleType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Перечисление типов правил.
     * DYNAMIC — правило можно изменять во время выполнения.
     * STATIC — встроенное правило, не подлежащее изменению.
     */
    public enum RuleType {
        DYNAMIC, STATIC
    }

    /**
     * Устанавливает дату создания и обновления при сохранении новой сущности.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Устанавливает дату обновления при изменении сущности.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Возвращает уникальный идентификатор правила.
     *
     * @return id правила
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор правила.
     *
     * @param id идентификатор правила
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название правила.
     *
     * @return название правила
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название правила.
     *
     * @param name название правила
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает условие правила в формате JSON.
     *
     * @return условие правила
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Устанавливает условие правила в формате JSON.
     *
     * @param condition условие правила
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Возвращает действие правила в формате JSON.
     *
     * @return действие правила
     */
    public String getAction() {
        return action;
    }

    /**
     * Устанавливает действие правила в формате JSON.
     *
     * @param action действие правила
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Возвращает приоритет правила (меньшее число — выше приоритет).
     *
     * @return приоритет правила
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Устанавливает приоритет правила.
     *
     * @param priority приоритет правила
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * Возвращает статус активации правила.
     *
     * @return true если правило активно
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Устанавливает статус активации правила.
     *
     * @param active статус активации
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Возвращает тип правила (DYNAMIC или STATIC).
     *
     * @return тип правила
     */
    public RuleType getRuleType() {
        return ruleType;
    }

    /**
     * Устанавливает тип правила.
     *
     * @param ruleType тип правила
     */
    public void setRuleType(RuleType ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * Возвращает дату создания правила.
     *
     * @return дата создания
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания правила.
     *
     * @param createdAt дата создания
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Возвращает дату последнего обновления правила.
     *
     * @return дата обновления
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Устанавливает дату последнего обновления правила.
     *
     * @param updatedAt дата обновления
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
