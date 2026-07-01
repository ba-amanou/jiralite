# JiraLite

A Project management application inspired by Jira.

## Why this project ? 

The goal is to practice and consolidate several technical concepts through a consistent end-to-end application: 

**Backend**
- Relational database modeling (enriched relationships, soft delete, audit log)
- JWT authentification and role-based access control with Spring Security
- Complex transactions with `@Transactional`

**Frontend**
- Feature-based Angular architecture (core / shared / features)
- Signals, `computed()`, `effect()`, `toSignal()`
- Guards, HTTP interceptors
- Integration with a secured API (JWT)

**AI-assisted development**

This project also serves as a testbed to practice a deliberate, reviewed workflow with [Claude Code](https://claude.com/claude-code) rather than unsupervised "vibe coding":
- Project context and conventions documented in [`CLAUDE.md`](./CLAUDE.md)
- Scoped tool permissions (`.claude/settings.json`) instead of blanket access
- A reproducible, sandboxed dev container (`Dockerfile.claude`) to run the agent

## Tech Stack
- **Backend**: Java 21, Spring Boot 4.x, Spring Security, JWT, H2, Flyway
- **Frontend**: Angular 21, Signals, PrimeNG
- **Tooling**: Claude Code (AI pair-programming, scoped permissions, containerized setup)

## Project status

- **Done**: JWT authentication (register / login), Spring Security setup, JPA auditing (`createdBy` / `createdAt`), soft-delete groundwork
- **In progress**: domain modeled (`Project`, `Sprint`, `Task`, `UserProject`, role-based access) but CRUD endpoints not implemented yet
- **In progress**: frontend is still the bare Angular CLI scaffold — feature UI and API integration not started

## Getting Started

### Prerequisites
- Java 21
- Node.js 20+ and npm
- Maven (or use the bundled `./mvnw`)

### Backend
```bash
cd backend
JWT_SECRET=<a-32-char-min-secret> ./mvnw spring-boot:run
```
Runs on `http://localhost:8080` (H2 in-memory DB, schema recreated on each start).

### Frontend
```bash
cd frontend
npm install
npm start
```
Runs on `http://localhost:4200`.

> This README will be updated as the project progresses.