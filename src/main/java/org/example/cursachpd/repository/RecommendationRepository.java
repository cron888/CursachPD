package org.example.cursachpd.repository;

import org.example.cursachpd.model.entity.Recommendation;
import org.example.cursachpd.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий для работы с сущностью Recommendation.
 * Предоставляет методы для поиска рекомендаций пользователя и подсчёта.
 */
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    /**
     * Находит рекомендации пользователя, отсортированные по дате создания (новые первыми).
     *
     * @param user пользователь
     * @return список рекомендаций пользователя
     */
    List<Recommendation> findByUserOrderByCreatedAtDesc(User user);

    /**
     * Подсчитывает количество рекомендаций конкретного пользователя.
     *
     * @param user пользователь
     * @return количество рекомендаций пользователя
     */
    long countByUser(User user);

    /**
     * Подсчитывает общее количество рекомендаций в системе.
     *
     * @return общее количество рекомендаций
     */
    long count();
}
