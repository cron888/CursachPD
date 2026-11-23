package org.example.cursachpd.config;

import org.example.cursachpd.model.entity.Rule;
import org.example.cursachpd.repository.RuleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Инициализатор начальных данных.
 * Создаёт стартовые правила (статические и динамические) при первом запуске приложения.
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private final RuleRepository ruleRepository;

    /**
     * Конструктор с внедрением зависимости RuleRepository.
     *
     * @param ruleRepository репозиторий правил
     */
    public DataInitializer(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    /**
     * Выполняет инициализацию начальных данных при запуске приложения.
     * Создаёт 3 стартовых правила, если база данных пуста.
     *
     * @param args аргументы запуска приложения
     */
    @Override
    public void run(ApplicationArguments args) {
        if (ruleRepository.count() == 0) {
            Rule programmingRule = new Rule();
            programmingRule.setName("Programming Category Rule");
            programmingRule.setCondition("{\"categories\": [\"programming\", \"development\"]}");
            programmingRule.setAction("{\"recommendation_tags\": [\"coding\", \"tutorials\", \"projects\"]}");
            programmingRule.setPriority(1);
            programmingRule.setActive(true);
            programmingRule.setRuleType(Rule.RuleType.STATIC);
            programmingRule.setCreatedAt(LocalDateTime.now());
            ruleRepository.save(programmingRule);

            Rule mathRule = new Rule();
            mathRule.setName("Mathematics Category Rule");
            mathRule.setCondition("{\"categories\": [\"mathematics\", \"algebra\", \"calculus\"]}");
            mathRule.setAction("{\"recommendation_tags\": [\"problems\", \"theory\", \"exercises\"]}");
            mathRule.setPriority(2);
            mathRule.setActive(true);
            mathRule.setRuleType(Rule.RuleType.STATIC);
            mathRule.setCreatedAt(LocalDateTime.now());
            ruleRepository.save(mathRule);

            Rule generalRule = new Rule();
            generalRule.setName("General Popularity Rule");
            generalRule.setCondition("{\"categories\": [\"*\"]}");
            generalRule.setAction("{\"recommendation_tags\": [\"trending\", \"popular\"]}");
            generalRule.setPriority(3);
            generalRule.setActive(true);
            generalRule.setRuleType(Rule.RuleType.DYNAMIC);
            generalRule.setCreatedAt(LocalDateTime.now());
            ruleRepository.save(generalRule);
        }
    }
}
