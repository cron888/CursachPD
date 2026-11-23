package org.example.cursachpd.controller;

import org.example.cursachpd.model.dto.RuleRequest;
import org.example.cursachpd.model.dto.RuleResponse;
import org.example.cursachpd.service.RuleService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuleControllerTest {

    @Test
    void createRule_shouldReturnCreatedResponse() {
        // Given
        RuleService service = mock(RuleService.class);
        RuleController controller = new RuleController(service);

        RuleResponse response = createRuleResponse(1L);
        when(service.createRule(any(RuleRequest.class))).thenReturn(response);

        // When
        RuleRequest request = new RuleRequest("Test Rule", "{\"categories\": []}",
                "{\"tags\": []}", 1, true);
        var result = controller.createRule(request);

        // Then
        assertEquals(201, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("Test Rule", result.getBody().getName());
    }

    @Test
    void getAllRules_shouldReturnAllRules() {
        // Given
        RuleService service = mock(RuleService.class);
        RuleController controller = new RuleController(service);
        when(service.getAllRules()).thenReturn(List.of(createRuleResponse(1L)));

        // When
        var result = controller.getAllRules();

        // Then
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void getActiveRules_shouldReturnActiveRules() {
        // Given
        RuleService service = mock(RuleService.class);
        RuleController controller = new RuleController(service);
        when(service.getActiveRules()).thenReturn(List.of(createRuleResponse(1L)));

        // When
        var result = controller.getActiveRules();

        // Then
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void deleteRule_shouldReturnNoContent() {
        // Given
        RuleService service = mock(RuleService.class);
        RuleController controller = new RuleController(service);
        doNothing().when(service).deleteRule(1L);

        // When
        var result = controller.deleteRule(1L);

        // Then
        assertEquals(204, result.getStatusCodeValue());
    }

    private RuleResponse createRuleResponse(Long id) {
        RuleResponse response = new RuleResponse();
        response.setId(id);
        response.setName("Test Rule");
        response.setCondition("{\"categories\": []}");
        response.setAction("{\"tags\": []}");
        response.setPriority(1);
        response.setActive(true);
        response.setRuleType("DYNAMIC");
        response.setCreatedAt(LocalDateTime.now());
        response.setUpdatedAt(LocalDateTime.now());
        return response;
    }
}
