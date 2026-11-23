package org.example.cursachpd.repository;

import org.example.cursachpd.model.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для работы с сущностью Rule.
 * Предоставляет методы для поиска активных правил по приоритету и по типу.
 */
public interface RuleRepository extends JpaRepository<Rule, Long> {

    /**
     * Находит все активные правила, отсортированные по приоритету (возрастание).
     *
     * @return список активных правил
     */
    List<Rule> findByActiveTrueOrderByPriorityAsc();

    /**
     * Находит все правила заданного типа.
     *
     * @param type тип правила (DYNAMIC или STATIC)
     * @return список правил заданного типа
     */
    List<Rule> findByRuleType(Rule.RuleType type);

    /**
     * Подсчитывает количество активных правил.
     *
     * @return количество активных правил
     */
    long countByActiveTrue();
}
