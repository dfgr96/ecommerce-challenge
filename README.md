# E-commerce Challenge

Este es un **monorepo** que contiene:

- `backend/` → API en Spring Boot
- `frontend/` → Aplicación web en Angular/React/Vue
- `docker-compose.yml` → Orquestación de backend + frontend + base de datos

## Cómo correrlo
### Opción 1: Local
- Levantar MySQL manualmente.
- Ejecutar backend con `mvn spring-boot:run`.
- Ejecutar frontend con `npm start` o `ng serve`.

### Opción 2: Docker
```bash
docker-compose up --build
