# Трекер задач GitHub Project

## Настройка GitHub Project

### Шаг 1: Создание проекта

1. Перейти на страницу GitHub репозитория
2. Нажать **Projects** → **New project**
3. Выбрать тип **Board** (Kanban-доска)
4. Назвать: `CursachPD — Task Tracker`

### Шаг 2: Колонки (статусы)

| Колонка | Значение | Описание |
|---------|----------|----------|
| `Backlog` | 0 | Задачи в очереди |
| `To Do` | 1 | Задачи к выполнению |
| `In Progress` | 2 | Задачи в работе |
| `In Review` | 3 | На проверке (MR) |
| `Done` | 4 | Выполнено |

### Шаг 3: Поля

| Поле | Тип | Назначение |
|------|-----|------------|
| Title | Text | Название задачи |
| Status | Single select | Статус (Backlog → Done) |
| Requirement | Text | ID требования (US-01, NFR-02 и т.д.) |
| Assignee | Person | Исполнитель |
| Labels | Multi-select | Метки (bug, feature, docs) |
| Linked MR | URL | Ссылка на merge request |
| Branch | Text | Имя ветки |

---

## Список задач проекта

### Модуль 1: Настройка проекта

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 1 | Настроить pom.xml с Spring Boot 3.2.5 | NFR-01 | ✅ Done | [#10](https://github.com/username/CursachPD/pull/10) | `setup/pom` |
| 2 | Добавить зависимости (JPA, Telegram, OpenAPI, Caffeine) | NFR-01, NFR-06 | ✅ Done | [#10](https://github.com/username/CursachPD/pull/10) | `setup/pom` |
| 3 | Создать checkstyle.xml для статического анализа | NFR-05 | ✅ Done | [#14](https://github.com/username/CursachPD/pull/14) | `quality/checkstyle` |
| 4 | Настроить application.yml с профилями dev/prod | NFR-01 | ✅ Done | [#10](https://github.com/username/CursachPD/pull/10) | `setup/config` |
| 5 | Создать CursachPdApplication.java | NFR-01 | ✅ Done | [#10](https://github.com/username/CursachPD/pull/10) | `setup/app` |

### Модуль 2: Domain Model

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 6 | Создать сущность User | US-01, US-02 | ✅ Done | [#11](https://github.com/username/CursachPD/pull/11) | `model/user` |
| 7 | Создать сущность Rule | US-03, US-04 | ✅ Done | [#11](https://github.com/username/CursachPD/pull/11) | `model/rule` |
| 8 | Создать сущность Recommendation | US-01 | ✅ Done | [#11](https://github.com/username/CursachPD/pull/11) | `model/recommendation` |
| 9 | Создать сущность Statistics | US-07 | ✅ Done | [#11](https://github.com/username/CursachPD/pull/11) | `model/statistics` |
| 10 | Создать DTO-классы (6 файлов) | US-01, US-03 | ✅ Done | [#11](https://github.com/username/CursachPD/pull/11) | `model/dto` |

### Модуль 3: Repositories

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 11 | Создать UserRepository | US-01, US-02 | ✅ Done | [#12](https://github.com/username/CursachPD/pull/12) | `repo/user` |
| 12 | Создать RuleRepository | US-03, US-06 | ✅ Done | [#12](https://github.com/username/CursachPD/pull/12) | `repo/rule` |
| 13 | Создать RecommendationRepository | US-01 | ✅ Done | [#12](https://github.com/username/CursachPD/pull/12) | `repo/recommendation` |
| 14 | Создать StatisticsRepository | US-07 | ✅ Done | [#12](https://github.com/username/CursachPD/pull/12) | `repo/statistics` |

### Модуль 4: Конфигурация

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 15 | Настроить CacheConfig (Caffeine) | NFR-03 | ✅ Done | [#13](https://github.com/username/CursachPD/pull/13) | `config/cache` |
| 16 | Настроить TelegramBotConfig | US-02 | ✅ Done | [#13](https://github.com/username/CursachPD/pull/13) | `config/bot` |
| 17 | Настроить OpenApiConfig | NFR-06 | ✅ Done | [#13](https://github.com/username/CursachPD/pull/13) | `config/openapi` |
| 18 | Создать DataInitializer (стартовые правила) | NFR-01 | ✅ Done | [#13](https://github.com/username/CursachPD/pull/13) | `config/init` |

### Модуль 5: Движки правил

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 19 | Реализовать StaticRuleEngine (4 правила) | NFR-01 | ✅ Done | [#15](https://github.com/username/CursachPD/pull/15) | `engine/static` |
| 20 | Реализовать DynamicRuleEngine | US-03, US-04 | ✅ Done | [#15](https://github.com/username/CursachPD/pull/15) | `engine/dynamic` |
| 21 | Реализовать RecommendationEngine | US-01, US-02 | ✅ Done | [#15](https://github.com/username/CursachPD/pull/15) | `engine/recommendation` |

### Модуль 6: Сервисы

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 22 | Реализовать RecommendationService (cache-aside) | NFR-02, NFR-03 | ✅ Done | [#16](https://github.com/username/CursachPD/pull/16) | `svc/recommendation` |
| 23 | Реализовать RuleService (CRUD) | US-03, US-04, US-05, US-06 | ✅ Done | [#16](https://github.com/username/CursachPD/pull/16) | `svc/rule` |
| 24 | Реализовать StatisticsService | US-07 | ✅ Done | [#16](https://github.com/username/CursachPD/pull/16) | `svc/statistics` |
| 25 | Реализовать CacheService | US-08 | ✅ Done | [#16](https://github.com/username/CursachPD/pull/16) | `svc/cache` |

### Модуль 7: REST API

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 26 | Реализовать RecommendationController | US-01 | ✅ Done | [#17](https://github.com/username/CursachPD/pull/17) | `api/recommendations` |
| 27 | Реализовать RuleController | US-03, US-04, US-05, US-06 | ✅ Done | [#17](https://github.com/username/CursachPD/pull/17) | `api/rules` |
| 28 | Реализовать StatisticsController | US-07 | ✅ Done | [#17](https://github.com/username/CursachPD/pull/17) | `api/statistics` |
| 29 | Реализовать CacheController | US-08, US-09 | ✅ Done | [#17](https://github.com/username/CursachPD/pull/17) | `api/cache` |

### Модуль 8: Telegram Bot

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 30 | Реализовать RecommendationBot | US-02 | ✅ Done | [#18](https://github.com/username/CursachPD/pull/18) | `bot/recommendation` |

### Модуль 9: Тесты

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 31 | Unit-тесты для сервисов (Recommendation, Rule, Statistics) | NFR-05 | ✅ Done | [#19](https://github.com/username/CursachPD/pull/19) | `test/services` |
| 32 | Unit-тесты для движков (Static, Dynamic RuleEngine) | NFR-05 | ✅ Done | [#19](https://github.com/username/CursachPD/pull/19) | `test/engine` |
| 33 | Controller-тесты (Recommendation, Rule, Cache) | NFR-05 | ✅ Done | [#19](https://github.com/username/CursachPD/pull/19) | `test/controllers` |

### Модуль 10: Документация

| № | Задача | Требование | Статус | MR | Ветка |
|---|--------|-----------|--------|-----|-------|
| 34 | README.md — главная страница | — | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/readme` |
| 35 | requirements.md — User Stories + NFR + Use Case | US-01..US-09, NFR-01..NFR-06 | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/requirements` |
| 36 | requirements-tracking.md — маппинг требований | US-01..US-09, NFR-01..NFR-06 | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/tracking` |
| 37 | architecture.md — Component + Activity Diagram | — | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/architecture` |
| 38 | api.md — OpenAPI документация | NFR-06 | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/api` |
| 39 | deployment.md — требования и инструкции | — | ✅ Done | [#20](https://github.com/username/CursachPD/pull/20) | `docs/deployment` |
| 40 | Javadoc для всех публичных методов | NFR-05 | ✅ Done | [#21](https://github.com/username/CursachPD/pull/21) | `docs/javadoc` |

---

## История перемещения задач по статусам

### Пример: Задача #26 (RecommendationController)

| Дата | Статус | Комментарий |
|------|--------|-------------|
| 2026-11-20 | Backlog | Задача создана в проекте |
| 2026-11-21 | To Do | Добавлена в спринт |
| 2026-11-21 | In Progress | Начата реализация |
| 2026-11-22 | In Review | MR отправлен на ревью |
| 2026-11-22 | Done | MR merged, задача закрыта |

### Общая статистика

| Статус | Количество |
|--------|-----------|
| Done | 40 |
| In Review | 0 |
| In Progress | 0 |
| To Do | 0 |
| Backlog | 0 |

**Всего задач:** 40
**Выполнено:** 40 (100%)

---

## Labels

| Label | Цвет | Описание |
|-------|------|----------|
| `feature` | 🟢 | Новая функциональность |
| `bug` | 🔴 | Исправление ошибки |
| `docs` | 🔵 | Документация |
| `refactor` | 🟡 | Рефакторинг |
| `test` | 🟣 | Тесты |
| `config` | 🟠 | Конфигурация |

---

## Workflow

```
Backlog → To Do → In Progress → In Review → Done
                                    ↑
                              (создать MR)
                                    |
                              (merge в main)
```

1. Создать Issue с описанием задачи
2. Добавить в GitHub Project с колонкой `Backlog`
3. Переместить в `To Do` при начале спринта
4. Переместить в `In Progress` при начале работы
5. Создать ветку и MR, добавить ссылку в поле `Linked MR`
6. Переместить в `In Review`
7. После мержа — переместить в `Done`
