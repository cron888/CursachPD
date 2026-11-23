package org.example.cursachpd.service;

import org.example.cursachpd.model.dto.RuleRequest;
import org.example.cursachpd.model.dto.RuleResponse;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RuleServiceTest {

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private RuleService ruleService;

    private RuleRequest ruleRequest;

    @BeforeEach
    void setUp() {
        ruleRequest = new RuleRequest();
        ruleRequest.setName("Test Rule");
        ruleRequest.setCondition("{\"categories\": [\"programming\"]}");
        ruleRequest.setAction("{\"tags\": [\"coding\"]}");
        ruleRequest.setPriority(1);
        ruleRequest.setActive(true);
    }

    @Test
    void createRule_shouldCreateAndReturnRule() {
        // Given
        Rule savedRule = createTestRule(1L);
        when(ruleRepository.save(any(Rule.class))).thenReturn(savedRule);

        // When
        RuleResponse response = ruleService.createRule(ruleRequest);

        // Then
        assertNotNull(response);
        assertEquals("Test Rule", response.getName());
        assertEquals("DYNAMIC", response.getRuleType());
        assertTrue(response.isActive());
        verify(ruleRepository).save(any(Rule.class));
    }

    @Test
    void updateRule_shouldUpdateAndReturnRule() {
        // Given
        Rule existingRule = createTestRule(1L);
        RuleRequest updateRequest = new RuleRequest();
        updateRequest.setName("Updated Rule");
        updateRequest.setCondition("{\"categories\": [\"math\"]}");
        updateRequest.setAction("{\"tags\": [\"problems\"]}");
        updateRequest.setPriority(2);
        updateRequest.setActive(false);

        Rule updatedRule = createTestRule(1L);
        updatedRule.setName("Updated Rule");
        updatedRule.setActive(false);

        when(ruleRepository.findById(1L)).thenReturn(Optional.of(existingRule));
        when(ruleRepository.save(any(Rule.class))).thenReturn(updatedRule);

        // When
        RuleResponse response = ruleService.updateRule(1L, updateRequest);

        // Then
        assertNotNull(response);
        assertEquals("Updated Rule", response.getName());
        assertFalse(response.isActive());
    }

    @Test
    void updateRule_shouldThrowExceptionWhenRuleNotFound() {
        // Given
        when(ruleRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> ruleService.updateRule(999L, ruleRequest));
    }

    @Test
    void deleteRule_shouldDeleteRule() {
        // Given
        when(ruleRepository.existsById(1L)).thenReturn(true);

        // When
        ruleService.deleteRule(1L);

        // Then
        verify(ruleRepository).deleteById(1L);
    }

    @Test
    void deleteRule_shouldThrowExceptionWhenRuleNotFound() {
        // Given
        when(ruleRepository.existsById(999L)).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> ruleService.deleteRule(999L));
    }

    @Test
    void getAllRules_shouldReturnAllRules() {
        // Given
        List<Rule> rules = List.of(createTestRule(1L), createTestRule(2L));
        when(ruleRepository.findAll()).thenReturn(rules);

        // When
        List<RuleResponse> responses = ruleService.getAllRules();

        // Then
        assertEquals(2, responses.size());
    }

    @Test
    void getActiveRules_shouldReturnOnlyActiveRules() {
        // Given
        List<Rule> activeRules = List.of(createTestRule(1L));
        when(ruleRepository.findByActiveTrueOrderByPriorityAsc()).thenReturn(activeRules);

        // When
        List<RuleResponse> responses = ruleService.getActiveRules();

        // Then
        assertEquals(1, responses.size());
        assertTrue(responses.get(0).isActive());
    }

    private Rule createTestRule(Long id) {
        Rule rule = new Rule();
        rule.setId(id);
        rule.setName("Test Rule");
        rule.setCondition("{\"categories\": [\"programming\"]}");
        rule.setAction("{\"tags\": [\"coding\"]}");
        rule.setPriority(1);
        rule.setActive(true);
        rule.setRuleType(Rule.RuleType.DYNAMIC);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        return rule;
    }
}
