package org.example.cursachpd.model.dto;

import java.time.LocalDateTime;

/**
 * DTO-ответ с информацией о правиле.
 * Содержит все поля правила, включая идентификатор, типы и временные метки.
 */
public class RuleResponse {

    private Long id;
    private String name;
    private String condition;
    private String action;
    private Integer priority;
    private boolean active;
    private String ruleType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public RuleResponse() {
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
     * Возвращает условие правила.
     *
     * @return условие правила
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Устанавливает условие правила.
     *
     * @param condition условие правила
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * Возвращает действие правила.
     *
     * @return действие правила
     */
    public String getAction() {
        return action;
    }

    /**
     * Устанавливает действие правила.
     *
     * @param action действие правила
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Возвращает приоритет правила.
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
    public String getRuleType() {
        return ruleType;
    }

    /**
     * Устанавливает тип правила.
     *
     * @param ruleType тип правила
     */
    public void setRuleType(String ruleType) {
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
