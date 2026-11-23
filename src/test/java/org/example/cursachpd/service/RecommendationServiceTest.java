package org.example.cursachpd.service;

import org.example.cursachpd.engine.RecommendationEngine;
import org.example.cursachpd.model.dto.RecommendationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @Mock
    private RecommendationEngine recommendationEngine;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private RecommendationService recommendationService;

    private List<String> categories;

    @BeforeEach
    void setUp() {
        categories = Arrays.asList("programming", "development");
    }

    @Test
    void getRecommendations_shouldReturnRecommendations() {
        // Given
        RecommendationDto dto = new RecommendationDto("Test Rec", "Description", "programming", "REST_API");
        List<RecommendationDto> recommendations = List.of(dto);
        when(recommendationEngine.generateRecommendations(anyString(), any())).thenReturn(recommendations);

        // When
        List<RecommendationDto> result = recommendationService.getRecommendations("testuser", categories);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Rec", result.get(0).getTitle());
        verify(recommendationEngine).generateRecommendations("testuser", categories);
    }

    @Test
    void getRecommendations_shouldReturnEmptyListWhenNoRecommendations() {
        // Given
        when(recommendationEngine.generateRecommendations(anyString(), any())).thenReturn(List.of());

        // When
        List<RecommendationDto> result = recommendationService.getRecommendations("testuser", categories);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void clearUserCache_shouldEvictCacheEntry() {
        // Given
        var cache = mock(org.springframework.cache.Cache.class);
        when(cacheManager.getCache("recommendations")).thenReturn(cache);

        // When
        recommendationService.clearUserCache("testuser");

        // Then
        verify(cache).evictIfPresent(anyString());
    }
}
