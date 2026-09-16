# JWT Auth (Study) Repo

Standalone Spring Boot project for learning JWT-based authentication with Spring Security.

## Stack
- Spring Boot 3, Spring Security, Spring Data JPA
- JWT via `jjwt`
- H2 (in-memory)

## Features
- Register / login with BCrypt-hashed passwords
- Stateless JWT auth (no server-side sessions)
- Role-based authorization via `@PreAuthorize`
- Task ownership checks (users can only edit/delete their own tasks)
- Custom `AuthenticationEntryPoint` for clean 401 responses

## Endpoints
| Method | Path            | Auth        |
|--------|-----------------|-------------|
| POST   | /auth/register  | public      |
| POST   | /auth/login     | public      |
| GET    | /auth/me        | authenticated |
| GET/POST/PUT/DELETE | /tasks     | authenticated (own tasks) |
| GET    | /users          | authenticated |
| PUT/DELETE | /users/{id} | ADMIN only  |

## Run
```bash
./gradlew bootRun
```

## Known limitations
- No refresh tokens / logout / token blacklist
- Task list endpoints don't filter by owner
- Secret key hardcoded — move to env variable before real deployment
