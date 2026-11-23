# REST API Documentation

## Base URL

```
http://localhost:8080/api
```

## Интерактивная документация

Swagger UI доступен по адресу: `http://localhost:8080/swagger-ui.html`

## Endpoints

### Recommendations

#### Получить рекомендации

```
POST /api/recommendations
```

**Request Body:**

```json
{
  "username": "john_doe",
  "categories": ["programming", "development"]
}
```

**Response 200 OK:**

```json
{
  "recommendations": [
    {
      "title": "Recommendation #1: Coding",
      "description": "Based on your interest in programming, development - suggested topic: coding",
      "category": "coding",
      "source": "REST_API"
    }
  ],
  "matchedRules": []
}
```

---

### Rules

#### Создать динамическое правило

```
POST /api/rules
```

**Request Body:**

```json
{
  "name": "My Custom Rule",
  "condition": "{\"categories\": [\"programming\"]}",
  "action": "{\"tags\": [\"coding\", \"tutorials\"]}",
  "priority": 1,
  "active": true
}
```

**Response 201 Created:**

```json
{
  "id": 1,
  "name": "My Custom Rule",
  "condition": "{\"categories\": [\"programming\"]}",
  "action": "{\"tags\": [\"coding\", \"tutorials\"]}",
  "priority": 1,
  "active": true,
  "ruleType": "DYNAMIC",
  "createdAt": "2026-11-23T10:00:00",
  "updatedAt": "2026-11-23T10:00:00"
}
```

#### Обновить динамическое правило

```
PUT /api/rules/{id}
```

#### Удалить динамическое правило

```
DELETE /api/rules/{id}
```

**Response 204 No Content**

#### Получить все правила

```
GET /api/rules
```

**Response 200 OK:**

```json
[
  {
    "id": 1,
    "name": "Programming Category Rule",
    "condition": "{\"categories\": [\"programming\", \"development\"]}",
    "action": "{\"recommendation_tags\": [\"coding\", \"tutorials\", \"projects\"]}",
    "priority": 1,
    "active": true,
    "ruleType": "STATIC",
    "createdAt": "2026-11-23T10:00:00",
    "updatedAt": "2026-11-23T10:00:00"
  }
]
```

#### Получить активные правила

```
GET /api/rules/active
```

---

### Statistics

#### Получить статистику приложения

```
GET /api/statistics
```

**Response 200 OK:**

```json
{
  "totalRecommendations": 150,
  "activeRules": 5,
  "totalUsers": 25,
  "recommendationsByCategory": {}
}
```

---

### Cache

#### Очистить кеш

```
POST /api/cache/clear
```

**Response 200 OK**

#### Получить информацию о приложении

```
GET /api/cache/info
```

**Response 200 OK:**

```json
{
  "appName": "CursachPD",
  "version": "1.0.0",
  "status": "RUNNING",
  "timestamp": "2026-11-23T10:00:00",
  "statistics": {
    "totalRecommendations": 150,
    "activeRules": 5,
    "totalUsers": 25
  }
}
```

---

## OpenAPI Specification

```yaml
openapi: 3.0.3
info:
  title: CursachPD Recommendations API
  description: REST API for the recommendations engine with static and dynamic rules
  version: 1.0.0
  contact:
    name: Course Project Team
servers:
  - url: http://localhost:8080
    description: Local server
paths:
  /api/recommendations:
    post:
      summary: Get recommendations for a user
      description: Returns personalized recommendations based on user categories
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/RecommendationRequest'
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RecommendationResponse'
  /api/rules:
    post:
      summary: Create a new dynamic rule
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/RuleRequest'
      responses:
        '201':
          description: Created
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RuleResponse'
    get:
      summary: Get all rules
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/RuleResponse'
  /api/rules/{id}:
    put:
      summary: Update an existing dynamic rule
      parameters:
        - in: path
          name: id
          required: true
          schema:
            type: integer
            format: int64
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/RuleRequest'
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/RuleResponse'
    delete:
      summary: Delete a dynamic rule
      parameters:
        - in: path
          name: id
          required: true
          schema:
            type: integer
            format: int64
      responses:
        '204':
          description: No Content
  /api/rules/active:
    get:
      summary: Get active rules
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/RuleResponse'
  /api/statistics:
    get:
      summary: Get application statistics
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/StatisticsResponse'
  /api/cache/clear:
    post:
      summary: Clear all cached recommendations
      responses:
        '200':
          description: OK
  /api/cache/info:
    get:
      summary: Get application info
      responses:
        '200':
          description: OK
          content:
            application/json:
              schema:
                type: object
components:
  schemas:
    RecommendationRequest:
      type: object
      required:
        - username
      properties:
        username:
          type: string
        categories:
          type: array
          items:
            type: string
    RecommendationResponse:
      type: object
      properties:
        recommendations:
          type: array
          items:
            $ref: '#/components/schemas/RecommendationDto'
        matchedRules:
          type: array
          items:
            type: string
    RecommendationDto:
      type: object
      properties:
        title:
          type: string
        description:
          type: string
        category:
          type: string
        source:
          type: string
    RuleRequest:
      type: object
      required:
        - name
        - condition
        - action
      properties:
        name:
          type: string
        condition:
          type: string
        action:
          type: string
        priority:
          type: integer
          minimum: 1
        active:
          type: boolean
    RuleResponse:
      type: object
      properties:
        id:
          type: integer
          format: int64
        name:
          type: string
        condition:
          type: string
        action:
          type: string
        priority:
          type: integer
        active:
          type: boolean
        ruleType:
          type: string
        createdAt:
          type: string
          format: date-time
        updatedAt:
          type: string
          format: date-time
    StatisticsResponse:
      type: object
      properties:
        totalRecommendations:
          type: integer
          format: int64
        activeRules:
          type: integer
          format: int64
        totalUsers:
          type: integer
          format: int64
        recommendationsByCategory:
          type: object
          additionalProperties:
            type: integer
            format: int64
```
