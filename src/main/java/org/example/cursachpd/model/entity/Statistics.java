package org.example.cursachpd.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность метрики статистики.
 * Хранит агрегированные метрики приложения: количество рекомендаций,
 * активных правил, пользователей и т.д.
 */
@Entity
@Table(name = "statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metric_name", nullable = false)
    private String metricName;

    @Column(name = "metric_value")
    private Long metricValue;

    @Column(name = "period")
    private String period;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    /**
     * Устанавливает дату записи метрики при сохранении новой сущности.
     */
    @PrePersist
    protected void onCreate() {
        this.recordedAt = LocalDateTime.now();
    }

    /**
     * Возвращает уникальный идентификатор записи статистики.
     *
     * @return id записи
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор записи статистики.
     *
     * @param id идентификатор записи
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название метрики (например, total_recommendations).
     *
     * @return название метрики
     */
    public String getMetricName() {
        return metricName;
    }

    /**
     * Устанавливает название метрики.
     *
     * @param metricName название метрики
     */
    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    /**
     * Возвращает текущее значение метрики.
     *
     * @return значение метрики
     */
    public Long getMetricValue() {
        return metricValue;
    }

    /**
     * Устанавливает текущее значение метрики.
     *
     * @param metricValue значение метрики
     */
    public void setMetricValue(Long metricValue) {
        this.metricValue = metricValue;
    }

    /**
     * Возвращает период метрики (DAILY, WEEKLY, MONTHLY).
     *
     * @return период
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Устанавливает период метрики.
     *
     * @param period период
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Возвращает дату записи метрики.
     *
     * @return дата записи
     */
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    /**
     * Устанавливает дату записи метрики.
     *
     * @param recordedAt дата записи
     */
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}
