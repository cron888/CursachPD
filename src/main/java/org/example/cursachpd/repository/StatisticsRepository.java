package org.example.cursachpd.repository;

import org.example.cursachpd.model.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью Statistics.
 * Предоставляет методы для поиска метрик по имени и периоду.
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    /**
     * Находит запись статистики по имени метрики и периоду.
     *
     * @param metricName название метрики
     * @param period период (DAILY, WEEKLY, MONTHLY)
     * @return Optional с найденной записью статистики
     */
    Optional<Statistics> findByMetricNameAndPeriod(String metricName, String period);
}
