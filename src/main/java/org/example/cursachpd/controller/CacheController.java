package org.example.cursachpd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.cursachpd.model.dto.StatisticsResponse;
import org.example.cursachpd.service.CacheService;
import org.example.cursachpd.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * REST-контроллер для управления кешем (актор External System).
 * Предоставляет эндпоинты для очистки кеша и получения информации о приложении.
 */
@RestController
@RequestMapping("/api/cache")
@Tag(name = "Cache", description = "Cache management for external systems")
public class CacheController {

    private final CacheService cacheService;
    private final StatisticsService statisticsService;

    /**
     * Конструктор с внедрением зависимостей.
     *
     * @param cacheService сервис кеша
     * @param statisticsService сервис статистики
     */
    public CacheController(CacheService cacheService,
                           StatisticsService statisticsService) {
        this.cacheService = cacheService;
        this.statisticsService = statisticsService;
    }

    /**
     * Очищает весь кеш рекомендаций.
     *
     * @return ResponseEntity со статусом 200 OK
     */
    @PostMapping("/clear")
    @Operation(summary = "Clear all cached recommendations", description = "Invalidates the entire recommendation cache")
    public ResponseEntity<Void> clearCache() {
        cacheService.clearAll();
        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает информацию о приложении для внешних систем.
     *
     * @return ResponseEntity с информацией о приложении и статистике (200)
     */
    @GetMapping("/info")
    @Operation(summary = "Get application info", description = "Returns application information for external systems")
    public ResponseEntity<Map<String, Object>> getAppInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("appName", "CursachPD");
        info.put("version", "1.0.0");
        info.put("status", "RUNNING");
        info.put("timestamp", LocalDateTime.now().toString());

        StatisticsResponse stats = statisticsService.getStatistics();
        Map<String, Object> statsMap = new HashMap<>();
        statsMap.put("totalRecommendations", stats.getTotalRecommendations());
        statsMap.put("activeRules", stats.getActiveRules());
        statsMap.put("totalUsers", stats.getTotalUsers());
        info.put("statistics", statsMap);

        return ResponseEntity.ok(info);
    }
}
