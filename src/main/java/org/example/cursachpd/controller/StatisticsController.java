package org.example.cursachpd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.cursachpd.model.dto.StatisticsResponse;
import org.example.cursachpd.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для статистики приложения (актор Manager).
 * Предоставляет эндпоинт для получения агрегированной статистики.
 */
@RestController
@RequestMapping("/api/statistics")
@Tag(name = "Statistics", description = "Application statistics and metrics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    /**
     * Конструктор с внедрением зависимости StatisticsService.
     *
     * @param statisticsService сервис статистики
     */
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    /**
     * Возвращает статистику приложения.
     *
     * @return ResponseEntity со статистикой (200)
     */
    @GetMapping
    @Operation(summary = "Get application statistics", description = "Returns overall application metrics and statistics")
    public ResponseEntity<StatisticsResponse> getStatistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}
