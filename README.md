# 🧠 Simple Psych CRM — CRM для психологов

![Java 17](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?logo=spring)
![Swagger](https://img.shields.io/badge/Swagger-UI-blue?logo=swagger)
![Tests](https://img.shields.io/badge/Tests-Passing-brightgreen)

Простая, но функциональная CRM-система для психологов: управление клиентами, учёт сессий, обработка ошибок и документированное API.

---

## ✅ Основные возможности

- **CRUD клиентов**: создание, просмотр, редактирование, удаление
- **CRUD сессий**: планирование, обновление, отмена
- **Валидация данных**: email, ФИО, телефон, дата сессии
- **Обработка ошибок**: стандартизированные JSON-ответы при ошибках
- **Аудит**: автоматическое заполнение `createdAt` / `updatedAt`
- **Документация API**: Swagger UI с интерактивными примерами
- **Тесты**: unit-тесты на основную бизнес-логику

---

## 🚀 Как запустить

### Требования

- Java 17+
- Maven 3.6+
- PostgreSQL (или H2 для тестов)

### Шаги

1. Клонируй репозиторий:
   ```bash
   git clone https://github.com/AndrewVarvarich/simple-psych-crm.git
   cd simple-psych-crm

2. Собери проект:
   ```bash
   mvn clean install

3. Запусти приложение:
   ```bash
   mvn spring-boot:run

4. Открой в браузере:
   - 📄 Swagger UI: http://localhost:8080/swagger-ui.html
   + 🏠 Приложение: http://localhost:8080/clients

## 🧩 Технологии

   + Spring Boot 3 — основа приложения
   + Spring Web — REST API
   + Spring Data JPA — работа с БД
   + PostgreSQL — основное хранилище
   + Lombok — уменьшение шаблонного кода
   + SpringDoc OpenAPI — Swagger-документация
   + JUnit 5 + Mockito — unit-тесты
   + SLF4J + Logback — логирование

## 📂 Структура проекта

---
```bash
src/main/java/crm/core/
├── client/
│   ├── controller/   → ClientController (REST API)
│   ├── service/      → ClientService + Impl (бизнес-логика)
│   ├── repositories/ → ClientRepository (JPA)
│   ├── dto/          → ClientDto (Data Transfer Object)
│   ├── mapper/       → ClientMapper (конвертация DTO ↔ Entity)
│   └── model/        → Client (сущность JPA с createdAt/updatedAt)
├── session/
│   ├── controller/   → SessionController (REST API)
│   ├── service/      → SessionService + Impl
│   ├── repositories/ → SessionRepository
│   ├── dto/          → SessionDto + Status (enum)
│   ├── mapper/       → SessionMapper
│   └── model/        → Session (сущность JPA)
└── exception/        → Глобальный обработчик ошибок + кастомные исключения
```
---

## ❗ Формат ошибок

При ошибках возвращается стандартизированный JSON:

```json
{
  "timestamp": "2025-09-07T12:34:56.789",
  "code": 404,
  "message": "Client with id 999 not found"
}
```
Поддерживаемые коды:

+ 400 Bad Request — ошибки валидации

+ 404 Not Found — клиент или сессия не найдены

---

## 🛣️ Roadmap

Проект находится в активной разработке. В ближайших планах:

- 📊 **Аналитика**
   - Endpoint `/stats/monthly` для подсчёта выручки и количества сессий
   - Фильтрация по статусу (DONE / PLANNED / CANCELED)

- 🛡️ **Безопасность**
   - Авторизация и аутентификация пользователей (Spring Security)
   - Разграничение доступа к данным

- 🐳 **Инфраструктура**
   - Dockerfile для контейнеризации приложения
   - Деплой на Render / Railway / Heroku

- 🔄 **CI/CD**
   - Автоматическая сборка и тестирование через GitHub Actions

- 🧪 **Тестирование**
   - Интеграционные тесты (Testcontainers, H2)
   - Повышение покрытия unit-тестами до 80%

- 🖥️ **UI (долгосрочно)**
   - Веб-интерфейс для удобного управления клиентами и сессиями

---

## 🤝 Автор

Разрабатывается с ❤️ для психологов — чтобы они могли сосредоточиться на людях, а не на бумагах.
