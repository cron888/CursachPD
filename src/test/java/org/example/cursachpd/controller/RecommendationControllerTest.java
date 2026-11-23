package org.example.cursachpd.controller;

import org.example.cursachpd.model.dto.RecommendationDto;
import org.example.cursachpd.model.dto.RecommendationRequest;
import org.example.cursachpd.model.dto.RecommendationResponse;
import org.example.cursachpd.service.RecommendationService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecommendationControllerTest {

    @Test
    void getRecommendations_shouldReturnResponse() {
        // Given
        RecommendationService service = mock(RecommendationService.class);
        RecommendationController controller = new RecommendationController(service);

        RecommendationDto dto = new RecommendationDto("Test Rec", "Description", "programming", "REST_API");
        when(service.getRecommendations("testuser", Arrays.asList("programming")))
                .thenReturn(List.of(dto));

        // When
        var request = new RecommendationRequest("testuser", Arrays.asList("programming"));
        var response = controller.getRecommendations(request);

        // Then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getRecommendations().size());
        assertEquals("Test Rec", response.getBody().getRecommendations().get(0).getTitle());
    }

    @Test
    void getRecommendations_shouldReturnEmptyList() {
        // Given
        RecommendationService service = mock(RecommendationService.class);
        RecommendationController controller = new RecommendationController(service);
        when(service.getRecommendations("testuser", List.of())).thenReturn(List.of());

        // When
        var request = new RecommendationRequest("testuser", List.of());
        var response = controller.getRecommendations(request);

        // Then
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getRecommendations().isEmpty());
    }
}
