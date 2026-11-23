# CursachPD — Recommendations Engine

## Описание проекта

Spring Boot приложение, реализующее движок рекомендаций с поддержкой статических и динамических правил. Пользователи могут получать рекомендации через REST API и Telegram Bot. Менеджеры управляют динамическими правилами и просматривают статистику. Внешние системы могут очищать кеш и получать информацию о приложении.

## Стек технологий

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA** (H2 для разработки, PostgreSQL для продакшена)
- **Caffeine Cache** — локальный кеш с TTL 30 минут
- **Telegram Bot API** (telegrambots 6.9.7.1)
- **SpringDoc OpenAPI** — Swagger UI доступен по адресу `/swagger-ui.html`
- **Maven** — система сборки
- **Checkstyle** — статический анализ кода

## Исполнители

- Имя исполнителя: [Ваше имя]
- Группа: [Ваша группа]
- Учебное заведение: [Ваше учебное заведение]

## Ссылки

- [Требования](requirements.md)
- [Отслеживание требований](requirements-tracking.md)
- [Архитектура](architecture.md)
- [REST API Documentation](api.md)
- [Развёртывание](deployment.md)

## Запуск проекта

```bash
# Сборка
mvn clean package

# Запуск
mvn spring-boot:run

# Или запуск JAR
java -jar target/CursachPD-1.0-SNAPSHOT.jar
```

## Доступные сервисы

| Сервис | URL |
|--------|-----|
| REST API | `http://localhost:8080/api/` |
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| H2 Console | `http://localhost:8080/h2-console` |
