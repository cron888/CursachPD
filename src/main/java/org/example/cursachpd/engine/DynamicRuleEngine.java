package org.example.cursachpd.engine;

import org.example.cursachpd.model.entity.Rule;
import org.example.cursachpd.repository.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Движок для оценки динамических правил из базы данных.
 * Правила оцениваются в порядке приоритета (меньшее число = выше приоритет).
 */
@Component
public class DynamicRuleEngine {

    private final RuleRepository ruleRepository;

    /**
     * Конструктор с внедрением зависимости RuleRepository.
     *
     * @param ruleRepository репозиторий правил
     */
    public DynamicRuleEngine(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    /**
     * Оценивает все активные динамические правила против категорий пользователя.
     * Возвращает совпавшие правила, отсортированные по приоритету.
     *
     * @param userCategories список категорий пользователя
     * @return список matched динамических правил
     */
    public List<Rule> evaluateRules(List<String> userCategories) {
        List<Rule> activeRules = ruleRepository.findByActiveTrueOrderByPriorityAsc();
        return activeRules.stream()
                .filter(rule -> matchesCondition(rule, userCategories))
                .collect(Collectors.toList());
    }

    /**
     * Оценивает все активные правила (DYNAMIC и STATIC типы).
     *
     * @param userCategories список категорий пользователя
     * @return список всех matched активных правил
     */
    public List<Rule> evaluateAllActiveRules(List<String> userCategories) {
        List<Rule> activeRules = ruleRepository.findByActiveTrueOrderByPriorityAsc();
        return activeRules.stream()
                .filter(rule -> matchesCondition(rule, userCategories))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, соответствует ли условие правила категориям пользователя.
     * Условие — JSON-строка вида {"categories": ["X", "Y"]}.
     * Wildcard "*" соответствует любой категории.
     *
     * @param rule правило для проверки
     * @param userCategories список категорий пользователя
     * @return true если условие совпадает с категориями
     */
    private boolean matchesCondition(Rule rule, List<String> userCategories) {
        String condition = rule.getCondition();
        if (condition == null || condition.isEmpty()) {
            return false;
        }

        try {
            // Simple JSON parsing for condition: {"categories": ["X", "Y"]}
            String categoriesStr = extractValue(condition, "categories");
            if (categoriesStr == null) {
                return false;
            }

            // Remove brackets and quotes, split by comma
            String[] ruleCategories = categoriesStr.replaceAll("[\\[\\]\"]", "")
                    .split(",");

            for (String rc : ruleCategories) {
                rc = rc.trim().toLowerCase();
                if (rc.equals("*")) {
                    return true;
                }
                for (String uc : userCategories) {
                    if (uc.toLowerCase().equals(rc)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // If parsing fails, log and skip this rule
            System.err.println("Failed to parse rule condition: " + condition);
        }

        return false;
    }

    /**
     * Простой парсер JSON для извлечения значения по ключу.
     *
     * @param json JSON-строка
     * @param key ключ для поиска
     * @return значение ключа или null
     */
    private String extractValue(String json, String key) {
        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        if (keyIndex == -1) {
            return null;
        }

        int valueStart = json.indexOf(':', keyIndex) + 1;
        while (valueStart < json.length() && json.charAt(valueStart) == ' ') {
            valueStart++;
        }

        if (valueStart >= json.length()) {
            return null;
        }

        char startChar = json.charAt(valueStart);
        if (startChar == '"') {
            // String value
            int endQuote = json.indexOf('"', valueStart + 1);
            return json.substring(valueStart + 1, endQuote);
        } else if (startChar == '[') {
            // Array value
            int endBracket = json.indexOf(']', valueStart);
            return json.substring(valueStart, endBracket + 1);
        }

        return null;
    }
}
