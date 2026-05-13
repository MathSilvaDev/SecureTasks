# SecureList

Fullstack task manager with JWT authentication. Users can register, login and manage only their own tasks.

The backend is the main focus of the project. The Angular frontend is a simple interface to use the API.

## Stack

**Backend**

- Java 25
- Spring Boot 4
- Spring Security
- OAuth2 Resource Server
- JWT with RSA keys
- PostgreSQL
- Docker
- JUnit and Mockito

**Frontend**

- Angular 20
- TypeScript
- SCSS

## Features

- User registration and login
- JWT authentication with Bearer Token
- Task creation, listing, completion and deletion
- User data isolation
- Global exception handling
- Unit tests for auth and task services

## Screenshots

![Login](./docs/login.png)
![Register](./docs/register.png)
![Home](./docs/home.png)

## Running Locally

Requirements: Java 25, Node.js, npm, Angular CLI, Docker and OpenSSL.

Create JWT keys in `backend/src/main/resources/keys`:

```bash
openssl genrsa -out jwt-private.key 4096
openssl rsa -in jwt-private.key -pubout -out jwt-public.key
```

From the project root, start PostgreSQL:

```bash
cd backend/docker
docker compose up -d
```

From the project root, run the backend:

```bash
cd backend
./mvnw spring-boot:run
```

On Windows:

```bash
cd backend
mvnw.cmd spring-boot:run
```

From the project root, run the frontend:

```bash
cd frontend
npm install
npm start
```

URLs:

```text
Backend:  http://localhost:8080
Frontend: http://localhost:4200
```

## API Overview

- `POST /api/auth/register` - create account
- `POST /api/auth/login` - login and receive JWT
- `GET /api/tasks/all` - list authenticated user's tasks
- `POST /api/tasks` - create task
- `PATCH /api/tasks/{id}/completed` - mark task as completed
- `DELETE /api/tasks/{id}` - delete task

## Project Structure

```text
backend/
|-- application
|   |-- auth
|   `-- task
|-- domain
|   |-- role
|   |-- task
|   `-- user
|-- infrastructure
|   |-- exception
|   `-- security
|-- common
`-- docker

frontend/
|-- auth
|-- home
|-- login
`-- register
```

## Author

Matheus Silva  
GitHub: [MathSilvaDev](https://github.com/MathSilvaDev)
