# Требования для развёртывания

## Необходимые сервисы

Для работы приложения в рабочей среде требуются:

| Сервис | Версия | Назначение |
|--------|--------|------------|
| JDK | 17+ | Java Runtime Environment |
| PostgreSQL | 14+ | Основное хранилище данных |
| (опционально) Redis | 6+ | Распределённый кеш (для production) |

## Сборка JAR-архива

### Требования

- Maven 3.8+
- JDK 17+

### Команды

```bash
# Сборка проекта
mvn clean package

# Или с пропуском тестов (для CI/CD)
mvn clean package -DskipTests

# Результат: target/CursachPD-1.0-SNAPSHOT.jar
```

## Запуск приложения

### Разработка (профиль dev)

```bash
# Запуск с Maven
mvn spring-boot:run

# Запуск JAR-архива
java -jar target/CursachPD-1.0-SNAPSHOT.jar
```

### Продакшен (профиль prod)

```bash
# Запуск с профилем prod
java -jar target/CursachPD-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### Docker (опционально)

```bash
# Сборка Docker-образа
docker build -t cursachpd:latest .

# Запуск контейнера
docker run -d \
  --name cursachpd \
  -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_HOST=postgres \
  -e DB_PORT=5432 \
  -e DB_NAME=cursachpd \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=secret \
  -e TELEGRAM_BOT_TOKEN=your-bot-token \
  cursachpd:latest
```

## Переменные среды (Environment Variables)

Переменные среды формируются из свойств `application.yml`. Правила преобразования:
- Точка `.` заменяется на `_`
- Имя приводится к верхнему регистру

### Основные переменные

| Переменная среды | Свойство application.yml | Описание | По умолчанию |
|-----------------|-------------------------|----------|-------------|
| `SERVER_PORT` | `server.port` | Порт для HTTP | `8080` |
| `TELEGRAM_BOT_TOKEN` | `telegram.bot.token` | Токен Telegram бота | `your-bot-token-here` |
| `TELEGRAM_BOT_USERNAME` | `telegram.bot.username` | Имя Telegram бота | `cursachpd_bot` |

### Переменные базы данных (профиль prod)

| Переменная среды | Свойство application.yml | Описание | По умолчанию |
|-----------------|-------------------------|----------|-------------|
| `DB_HOST` | `spring.datasource.url` (jdbc:postgresql://${DB_HOST}...) | Хост PostgreSQL | `localhost` |
| `DB_PORT` | `spring.datasource.url` (jdbc:postgresql://...${DB_PORT}...) | Порт PostgreSQL | `5432` |
| `DB_NAME` | `spring.datasource.url` (jdbc:postgresql://.../${DB_NAME}) | Имя базы данных | `cursachpd` |
| `DB_USERNAME` | `spring.datasource.username` | Имя пользователя БД | `postgres` |
| `DB_PASSWORD` | `spring.datasource.password` | Пароль БД | `postgres` |

### Полный пример .env файла

```env
# Профиль
SPRING_PROFILES_ACTIVE=prod

# База данных
DB_HOST=postgres
DB_PORT=5432
DB_NAME=cursachpd
DB_USERNAME=postgres
DB_PASSWORD=your-secure-password

# Telegram Bot
TELEGRAM_BOT_TOKEN=your-telegram-bot-token
TELEGRAM_BOT_USERNAME=cursachpd_bot

# Сервер
SERVER_PORT=8080
```

## Проверка работоспособности

```bash
# Проверка здоровья приложения
curl http://localhost:8080/api/cache/info

# Ожидаемый ответ:
# {"appName":"CursachPD","version":"1.0.0","status":"RUNNING",...}
```

## Структура конфигурации Spring Boot

```
application.yml          # Общие настройки
  ├─ application-dev.yml  # Профиль разработки (H2)
  └─ application-prod.yml # Профиль продакшена (PostgreSQL)
```

### Профиль dev (по умолчанию)

- Встроенная база H2
- H2 Console включён (`/h2-console`)
- DDL auto: `update`
- SQL-запросы выводятся в лог

### Профиль prod

- Внешняя база PostgreSQL
- DDL auto: `validate` (запрет на автоизменение схемы)
- Оптимизированные настройки JPA
