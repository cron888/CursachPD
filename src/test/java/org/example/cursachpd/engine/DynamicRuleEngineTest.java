package org.example.cursachpd.engine;

import org.example.cursachpd.model.entity.Rule;
import org.example.cursachpd.repository.RuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DynamicRuleEngineTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private DynamicRuleEngine dynamicRuleEngine;

    @BeforeEach
    void setUp() {
        // No setup needed - using mocked repository
    }

    @Test
    void evaluateRules_shouldReturnMatchingRules() {
        // Given
        Rule rule = new Rule();
        rule.setId(1L);
        rule.setName("Test Rule");
        rule.setCondition("{\"categories\": [\"programming\"]}");
        rule.setAction("{\"tags\": [\"coding\"]}");
        rule.setPriority(1);
        rule.setActive(true);
        rule.setRuleType(Rule.RuleType.DYNAMIC);
        rule.setCreatedAt(LocalDateTime.now());

        when(ruleRepository.findByActiveTrueOrderByPriorityAsc()).thenReturn(List.of(rule));

        // When
        List<Rule> result = dynamicRuleEngine.evaluateRules(List.of("programming", "development"));

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Rule", result.get(0).getName());
    }

    @Test
    void evaluateRules_shouldReturnEmptyWhenNoMatch() {
        // Given
        Rule rule = new Rule();
        rule.setId(1L);
        rule.setName("Math Rule");
        rule.setCondition("{\"categories\": [\"mathematics\"]}");
        rule.setAction("{\"tags\": [\"problems\"]}");
        rule.setPriority(1);
        rule.setActive(true);
        rule.setRuleType(Rule.RuleType.DYNAMIC);
        rule.setCreatedAt(LocalDateTime.now());

        when(ruleRepository.findByActiveTrueOrderByPriorityAsc()).thenReturn(List.of(rule));

        // When
        List<Rule> result = dynamicRuleEngine.evaluateRules(List.of("programming", "development"));

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void evaluateRules_shouldMatchWildcard() {
        // Given
        Rule rule = new Rule();
        rule.setId(1L);
        rule.setName("General Rule");
        rule.setCondition("{\"categories\": [\"*\"]}");
        rule.setAction("{\"tags\": [\"general\"]}");
        rule.setPriority(1);
        rule.setActive(true);
        rule.setRuleType(Rule.RuleType.DYNAMIC);
        rule.setCreatedAt(LocalDateTime.now());

        when(ruleRepository.findByActiveTrueOrderByPriorityAsc()).thenReturn(List.of(rule));

        // When
        List<Rule> result = dynamicRuleEngine.evaluateRules(List.of("any", "categories"));

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("General Rule", result.get(0).getName());
    }

    @Test
    void evaluateRules_shouldReturnEmptyWhenRepositoryEmpty() {
        // Given
        when(ruleRepository.findByActiveTrueOrderByPriorityAsc()).thenReturn(List.of());

        // When
        List<Rule> result = dynamicRuleEngine.evaluateRules(List.of("programming"));

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
