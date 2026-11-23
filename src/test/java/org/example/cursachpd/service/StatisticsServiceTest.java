package org.example.cursachpd.service;

import org.example.cursachpd.repository.RecommendationRepository;
import org.example.cursachpd.repository.RuleRepository;
import org.example.cursachpd.repository.StatisticsRepository;
import org.example.cursachpd.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    void recordRecommendationGenerated_shouldIncrementMetric() {
        // Given
        when(statisticsRepository.findByMetricNameAndPeriod("total_recommendations", "DAILY"))
                .thenReturn(java.util.Optional.empty());

        // When
        statisticsService.recordRecommendationGenerated();

        // Then
        verify(statisticsRepository).save(any());
    }

    @Test
    void getStatistics_shouldReturnStatistics() {
        // Given
        when(recommendationRepository.count()).thenReturn(100L);
        when(ruleRepository.countByActiveTrue()).thenReturn(5L);
        when(userRepository.count()).thenReturn(10L);

        // When
        var stats = statisticsService.getStatistics();

        // Then
        assertNotNull(stats);
        assertEquals(100, stats.getTotalRecommendations());
        assertEquals(5, stats.getActiveRules());
        assertEquals(10, stats.getTotalUsers());
    }
}
