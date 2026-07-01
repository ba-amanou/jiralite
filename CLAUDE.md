# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Stack

JiraLite — Jira-like project management app.

- **Backend** (`backend/`, Maven): Java 21, Spring Boot 4, Spring Security, JWT (jjwt), H2, Flyway (on classpath but disabled — `spring.flyway.enabled=false`, schema comes from `ddl-auto=create-drop`).
- **Frontend** (`frontend/`, Angular CLI): Angular 21, Signals, PrimeNG, Vitest. Currently a bare CLI scaffold — the `core/shared/features` structure described in `frontend/README.md` isn't built yet.

The two apps build and run independently; there's no root build tool.

## Commands

Backend (from `backend/`):
- `mvn test` — run all tests
- `mvn -pl backend test -Dtest=ClassName#methodName` — run a single test
- `mvn spring-boot:run` — run the API (port 8080, `dev` profile)

Frontend (from `frontend/`):
- `npm start` — dev server on `http://localhost:4200`
- `npm run build` — production build to `dist/`
- `npm test` — unit tests via Vitest

## Key conventions

- Entity hierarchy: `BaseEntity` (UUID id) → `TimestampedEntity` (`createdAt`) → `AuditableEntity` (`createdBy`, filled via JPA auditing off the security context). Soft-delete entities add their own `deletedAt` + `@SQLRestriction` — not built into the base classes.
- The Spring Security "username" is the user's **UUID**, not their email — `UserDetailsServiceImpl` loads by email only for the login step; everywhere else (JWT subject, `Authentication#getName()`) it's the UUID.
- Controllers stay thin and delegate to `service/`; `@Transactional` boundaries live in services, not controllers.
- Domain errors extend `ApiException(message, HttpStatus)`, handled centrally by `GlobalExceptionHandler` — don't throw generic exceptions or set status codes in controllers.
- `application-*.properties` (profile-specific, holds secrets) are gitignored and permission-denied to read — ask the user for values instead of trying to open them.
