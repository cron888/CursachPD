package org.example.cursachpd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.cursachpd.model.dto.RecommendationRequest;
import org.example.cursachpd.model.dto.RecommendationResponse;
import org.example.cursachpd.model.dto.RecommendationDto;
import org.example.cursachpd.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для получения рекомендаций (актор User).
 * Предоставляет эндпоинт для получения персонализированных рекомендаций.
 */
@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations", description = "Endpoints for receiving recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    /**
     * Конструктор с внедрением зависимости RecommendationService.
     *
     * @param recommendationService сервис рекомендаций
     */
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Получает рекомендации для пользователя.
     *
     * @param request запрос с именем пользователя и категориями
     * @response ResponseEntity с рекомендациями и matched правилами
     */
    @PostMapping
    @Operation(summary = "Get recommendations for a user", description = "Returns personalized recommendations based on user categories")
    public ResponseEntity<RecommendationResponse> getRecommendations(
            @RequestBody RecommendationRequest request) {
        List<RecommendationDto> recommendations = recommendationService.getRecommendations(
                request.getUsername(), request.getCategories());
        RecommendationResponse response = new RecommendationResponse(recommendations, List.of());
        return ResponseEntity.ok(response);
    }
}
