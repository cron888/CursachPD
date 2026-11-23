package org.example.cursachpd.repository;

import org.example.cursachpd.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью User.
 * Предоставляет методы для поиска пользователей по имени и идентификатору чата Telegram.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по имени.
     *
     * @param username имя пользователя
     * @return Optional с найденным пользователем
     */
    Optional<User> findByUsername(String username);

    /**
     * Находит пользователя по идентификатору чата Telegram.
     *
     * @param chatId идентификатор чата Telegram
     * @return Optional с найденным пользователем
     */
    Optional<User> findByTelegramChatId(Long chatId);
}
