package org.example.cursachpd.service;

import org.example.cursachpd.model.dto.RuleRequest;
import org.example.cursachpd.model.dto.RuleResponse;
import org.example.cursachpd.model.entity.Rule;
import org.example.cursachpd.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для CRUD-операций над динамическими правилами.
 * Предоставляет методы создания, обновления, удаления и получения правил.
 */
@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    /**
     * Конструктор с внедрением зависимости RuleRepository.
     *
     * @param ruleRepository репозиторий правил
     */
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    /**
     * Создаёт новое динамическое правило.
     *
     * @param request запрос на создание правила
     * @response созданный объект RuleResponse
     */
    public RuleResponse createRule(RuleRequest request) {
        Rule rule = new Rule();
        rule.setName(request.getName());
        rule.setCondition(request.getCondition());
        rule.setAction(request.getAction());
        rule.setPriority(request.getPriority());
        rule.setActive(request.isActive());
        rule.setRuleType(Rule.RuleType.DYNAMIC);
        rule = ruleRepository.save(rule);
        return toResponse(rule);
    }

    /**
     * Обновляет существующее динамическое правило.
     *
     * @param id идентификатор правила
     * @param request запрос с новыми данными
     * @return обновлённый объект RuleResponse
     * @throws IllegalArgumentException если правило не найдено
     */
    public RuleResponse updateRule(Long id, RuleRequest request) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: " + id));

        rule.setName(request.getName());
        rule.setCondition(request.getCondition());
        rule.setAction(request.getAction());
        rule.setPriority(request.getPriority());
        rule.setActive(request.isActive());
        rule = ruleRepository.save(rule);
        return toResponse(rule);
    }

    /**
     * Удаляет динамическое правило.
     *
     * @param id идентификатор правила
     * @throws IllegalArgumentException если правило не найдено
     */
    public void deleteRule(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new IllegalArgumentException("Rule not found with id: " + id);
        }
        ruleRepository.deleteById(id);
    }

    /**
     * Возвращает все правила.
     *
     * @return список всех правил
     */
    public List<RuleResponse> getAllRules() {
        return ruleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает все активные правила, отсортированные по приоритету.
     *
     * @return список активных правил
     */
    public List<RuleResponse> getActiveRules() {
        return ruleRepository.findByActiveTrueOrderByPriorityAsc().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает одно правило по идентификатору.
     *
     * @param id идентификатор правила
     * @return объект RuleResponse
     * @throws IllegalArgumentException если правило не найдено
     */
    public RuleResponse getRuleById(Long id) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rule not found with id: " + id));
        return toResponse(rule);
    }

    /**
     * Преобразует сущность Rule в DTO RuleResponse.
     *
     * @param rule сущность правила
     * @return DTO правила
     */
    private RuleResponse toResponse(Rule rule) {
        RuleResponse response = new RuleResponse();
        response.setId(rule.getId());
        response.setName(rule.getName());
        response.setCondition(rule.getCondition());
        response.setAction(rule.getAction());
        response.setPriority(rule.getPriority());
        response.setActive(rule.isActive());
        response.setRuleType(rule.getRuleType() != null ? rule.getRuleType().name() : null);
        response.setCreatedAt(rule.getCreatedAt());
        response.setUpdatedAt(rule.getUpdatedAt());
        return response;
    }
}
