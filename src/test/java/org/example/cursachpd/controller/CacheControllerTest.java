package org.example.cursachpd.controller;

import org.example.cursachpd.service.CacheService;
import org.example.cursachpd.service.StatisticsService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CacheControllerTest {

    @Test
    void clearCache_shouldClearCacheAndReturnOk() {
        // Given
        CacheService cacheService = mock(CacheService.class);
        StatisticsService statisticsService = mock(StatisticsService.class);
        CacheController controller = new CacheController(cacheService, statisticsService);

        // When
        var result = controller.clearCache();

        // Then
        verify(cacheService).clearAll();
    }
}
