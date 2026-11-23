# CursachPD — Recommendations Engine

## Описание проекта

**CursachPD** — Spring Boot приложение, реализующее движок рекомендаций с поддержкой статических и динамических правил. Пользователи получают персонализированные рекомендации через REST API и Telegram Bot. Менеджеры управляют динамическими правилами и просматривают статистику. Внешние системы могут очищать кеш и получать информацию о приложении.

## Стек технологий

| Компонент | Технология |
|-----------|-----------|
| Язык | Java 17 |
| Фреймворк | Spring Boot 3.2.5 |
| ORM | Spring Data JPA (Hibernate) |
| База данных | H2 (dev), PostgreSQL (prod) |
| Кеш | Caffeine (30 мин TTL, 1000 элементов) |
| Telegram Bot | telegrambots 6.9.7.1 |
| API Docs | SpringDoc OpenAPI 2.5.0 (Swagger UI) |
| Сборка | Maven |
| Статический анализ | Checkstyle |

## Исполнитель

- **ФИО:** [Ваше имя]
- **Группа:** [Ваша группа]
- **Учебное заведение:** [Ваше учебное заведение]

## Быстрый старт

### Требования

- JDK 17+
- Maven 3.8+

### Сборка и запуск

```bash
# Сборка проекта
mvn clean package

# Запуск (профиль dev, H2)
mvn spring-boot:run

# Запуск JAR
java -jar target/CursachPD-1.0-SNAPSHOT.jar

# Запуск с профилем prod
java -jar target/CursachPD-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### Доступные сервисы

| Сервис | URL |
|--------|-----|
| REST API | `http://localhost:8080/api/` |
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| H2 Console | `http://localhost:8080/h2-console` |

## API Endpoints

| Метод | Endpoint | Описание | Актор |
|-------|----------|----------|-------|
| POST | `/api/recommendations` | Получить рекомендации | User |
| POST | `/api/rules` | Создать правило | Manager |
| PUT | `/api/rules/{id}` | Обновить правило | Manager |
| DELETE | `/api/rules/{id}` | Удалить правило | Manager |
| GET | `/api/rules` | Получить все правила | Manager |
| GET | `/api/rules/active` | Получить активные правила | Manager |
| GET | `/api/statistics` | Получить статистику | Manager |
| POST | `/api/cache/clear` | Очистить кеш | External System |
| GET | `/api/cache/info` | Получить информацию | External System |

## Структура проекта

```
src/main/java/org/example/cursachpd/
├── CursachPdApplication.java       # Точка входа
├── bot/                            # Telegram бот
│   └── RecommendationBot.java
├── config/                         # Конфигурация
│   ├── CacheConfig.java
│   ├── DataInitializer.java
│   ├── OpenApiConfig.java
│   └── TelegramBotConfig.java
├── controller/                     # REST контроллеры
│   ├── CacheController.java
│   ├── RecommendationController.java
│   ├── RuleController.java
│   └── StatisticsController.java
├── engine/                         # Движки правил
│   ├── DynamicRuleEngine.java
│   ├── RecommendationEngine.java
│   └── StaticRuleEngine.java
├── model/
│   ├── dto/                        # DTO
│   │   ├── RecommendationDto.java
│   │   ├── RecommendationRequest.java
│   │   ├── RecommendationResponse.java
│   │   ├── RuleRequest.java
│   │   ├── RuleResponse.java
│   │   └── StatisticsResponse.java
│   └── entity/                     # JPA сущности
│       ├── Recommendation.java
│       ├── Rule.java
│       ├── Statistics.java
│       └── User.java
├── repository/                     # JPA репозитории
│   ├── RecommendationRepository.java
│   ├── RuleRepository.java
│   ├── StatisticsRepository.java
│   └── UserRepository.java
└── service/                        # Сервисы
    ├── CacheService.java
    ├── RecommendationService.java
    ├── RuleService.java
    └── StatisticsService.java
```

## Документация

- [📐 Архитектура приложения](docs/wiki/architecture.md) — диаграммы компонентов и деятельности
- [📋 Требования](docs/wiki/requirements.md) — User Stories, NFR, Use Case диаграмма
- [📊 Трекер требований](docs/wiki/requirements-tracking.md) — маппинг требований на GitHub Issues
- [📡 REST API](docs/wiki/api.md) — полная документация API + OpenAPI спецификация
- [🚀 Развёртывание](docs/wiki/deployment.md) — требования, команды, переменные среды

## Тесты

```bash
# Запуск всех тестов
mvn test

# Результаты: 28 тестов, 0 ошибок
```

## Лицензия

Учебный проект.
