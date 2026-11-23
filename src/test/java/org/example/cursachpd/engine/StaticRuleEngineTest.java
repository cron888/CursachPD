package org.example.cursachpd.engine;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StaticRuleEngineTest {

    private final StaticRuleEngine engine = new StaticRuleEngine();

    @Test
    void evaluateCategoryRule_shouldReturnMatchingTags() {
        // Given
        List<String> categories = Arrays.asList("programming", "development");

        // When
        Map<String, Object> result = engine.evaluateCategoryRule(categories);

        // Then
        assertNotNull(result);
        assertEquals("CategoryRule", result.get("ruleName"));
        assertNotNull(result.get("matchedTags"));
        List<String> tags = (List<String>) result.get("matchedTags");
        assertFalse(tags.isEmpty());
    }

    @Test
    void evaluateTimeBasedRule_shouldReturnTimeOfDay() {
        // When
        Map<String, Object> result = engine.evaluateTimeBasedRule();

        // Then
        assertNotNull(result);
        assertEquals("TimeBasedRule", result.get("ruleName"));
        assertNotNull(result.get("timeOfDay"));
        assertNotNull(result.get("recommendationType"));
    }

    @Test
    void evaluateFrequencyRule_shouldReturnBlockedTags() {
        // Given
        List<String> previouslyRecommended = Arrays.asList("coding", "tutorials");

        // When
        Map<String, Object> result = engine.evaluateFrequencyRule(previouslyRecommended);

        // Then
        assertNotNull(result);
        assertEquals("FrequencyRule", result.get("ruleName"));
        assertNotNull(result.get("blockedTags"));
    }

    @Test
    void evaluatePopularityRule_shouldReturnTrendingTags() {
        // When
        Map<String, Object> result = engine.evaluatePopularityRule();

        // Then
        assertNotNull(result);
        assertEquals("PopularityRule", result.get("ruleName"));
        assertNotNull(result.get("trendingTags"));
    }

    @Test
    void evaluateAll_shouldReturnAllRuleResults() {
        // Given
        List<String> categories = Arrays.asList("programming");
        List<String> previouslyRecommended = List.of();

        // When
        Map<String, Object> result = engine.evaluateAll(categories, previouslyRecommended);

        // Then
        assertNotNull(result);
        assertTrue(result.containsKey("categoryRule"));
        assertTrue(result.containsKey("timeBasedRule"));
        assertTrue(result.containsKey("frequencyRule"));
        assertTrue(result.containsKey("popularityRule"));
    }
}
