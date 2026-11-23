package org.example.cursachpd.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.cursachpd.model.dto.RuleRequest;
import org.example.cursachpd.model.dto.RuleResponse;
import org.example.cursachpd.service.RuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления динамическими правилами (актор Manager).
 * Предоставляет CRUD-операции для создания, обновления, удаления и просмотра правил.
 */
@RestController
@RequestMapping("/api/rules")
@Tag(name = "Rules", description = "CRUD operations for dynamic rules")
public class RuleController {

    private final RuleService ruleService;

    /**
     * Конструктор с внедрением зависимости RuleService.
     *
     * @param ruleService сервис правил
     */
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    /**
     * Создаёт новое динамическое правило.
     *
     * @param request запрос на создание правила
     * @return ResponseEntity с созданным правилом (201)
     */
    @PostMapping
    @Operation(summary = "Create a new dynamic rule")
    public ResponseEntity<RuleResponse> createRule(@Valid @RequestBody RuleRequest request) {
        RuleResponse response = ruleService.createRule(request);
        return ResponseEntity.status(201).body(response);
    }

    /**
     * Обновляет существующее динамическое правило.
     *
     * @param id идентификатор правила
     * @param request запрос с новыми данными
     * @return ResponseEntity с обновлённым правилом (200)
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing dynamic rule")
    public ResponseEntity<RuleResponse> updateRule(@PathVariable Long id,
                                                   @Valid @RequestBody RuleRequest request) {
        RuleResponse response = ruleService.updateRule(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Удаляет динамическое правило.
     *
     * @param id идентификатор правила
     * @return ResponseEntity со статусом 204 No Content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a dynamic rule")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Возвращает все правила.
     *
     * @return ResponseEntity со списком всех правил (200)
     */
    @GetMapping
    @Operation(summary = "Get all rules")
    public ResponseEntity<List<RuleResponse>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    /**
     * Возвращает все активные правила.
     *
     * @return ResponseEntity со списком активных правил (200)
     */
    @GetMapping("/active")
    @Operation(summary = "Get active rules")
    public ResponseEntity<List<RuleResponse>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
}
