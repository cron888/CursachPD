package org.example.cursachpd.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO-запрос для создания или обновления правила.
 * Содержит название, условие, действие, приоритет и статус активации.
 */
public class RuleRequest {

    @NotBlank(message = "Rule name is required")
    private String name;

    @NotBlank(message = "Rule condition is required")
    private String condition;

    @NotBlank(message = "Rule action is required")
    private String action;

    @Min(value = 1, message = "Priority must be at least 1")
    private Integer priority;

    private boolean active = true;

    /**
     * Конструктор по умолчанию для десериализации.
     */
    public RuleRequest() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name название правила
     * @param condition условие правила
     * @param action действие правила
     * @param priority приоритет правила
     * @param active статус активации
     */
    public RuleRequest(String name, String condition, String action, Integer priority, boolean active) {
        this.name = name;
        this.condition = condition;
        this.action = action;
        this.priority = priority;
        this.active = active;
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
}
