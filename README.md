# SecureList - Task Manager (Fullstack)

Fullstack task management application with secure JWT-based authentication and user data isolation.
Each user has their own tasks and can create, list, mark as completed, and delete them.

> Backend is the main focus (Spring Security + JWT + OAuth2 Resource Server).
> Frontend built with Angular only consumes and displays the API data.

---

## Technologies

### Backend

* Java 25 (LTS)
* Spring Boot
* Spring Security
* OAuth2 Resource Server
* JWT (RSA)
* PostgreSQL
* Docker (PostgreSQL container)

### Frontend

* Angular
* TypeScript
* HTML / SCSS

---

## 📦 Features

* User registration
* Login with JWT token generation
* Authentication via Bearer Token (JWT - RSA signed)
* Task management:

  * Create task
  * List user tasks
  * Mark as completed
  * Delete task
* User data isolation
* Global exception handling

---

## 🔗 API Endpoints

### Auth
POST /api/auth/register  
Request: { "email": "example@example.com", "username": "at least 3 characters", "password": "at least 8 characters" }

POST /api/auth/login  
Request: { "email": "example@example.com", "password": "yourPassword" }  
Response: { "token": "JWT_TOKEN", "expiresAt": 300 }

### Tasks
GET /api/tasks/all  

POST /api/tasks  
Request: { "title": "at least 3 characters", "description": "at least 3 characters" }

PATCH /api/tasks/{id}/completed  
DELETE /api/tasks/{id}  

### Notes
- Authentication via JWT (Bearer Token)
- Use: Authorization: Bearer <token>
- userId is extracted from the JWT
- All task endpoints require authentication
---

## 🧪 Testing

* Unit tests written with JUnit 5 and Mockito
* Unit tests covering AuthService and TaskService
* Mocking used to isolate services from repositories and external dependencies

---

## ⚙️ Requirements

Install:

* Java 25
* Node.js 22
* npm 11
* Angular CLI
* OpenSSL
* Docker

---

## 🔐 JWT Key Configuration

Inside:

```
src/main/resources/keys
```

### 1. Generate private key

```
openssl genrsa -out jwt-private.key 4096
```

### 2. Generate public key

```
openssl rsa -in jwt-private.key -pubout -out jwt-public.key
```

> These keys are required for JWT authentication to work.

---

## 🗄️ Database

The project uses PostgreSQL as the main database, running in a Docker container.

### Configuration

- Host: localhost
- Port: 5432
- Database: securelist
- Username: postgres
- Password: postgres

### Docker Setup

The database configuration is located at:

backend/docker/docker-compose.yaml

The container is based on PostgreSQL and is automatically configured with the credentials above.

### Notes

- The database must be running before starting the backend
- Tables are automatically created by Hibernate (`ddl-auto: update`)


---

Follow the steps below to run the full application.

---

### 1. 🗄️ Start Database (Docker)

Inside the backend folder:

```
cd docker
```
```
docker compose up -d
```

---

### 2. ⚙️ Run Backend (Spring Boot)

Run the application:

```
./mvnw spring-boot:run
```

Or run directly from your IDE.

Backend will be available at:

```
http://localhost:8080
```

---

### 3. 🌐 Run Frontend (Angular)

Inside the frontend folder:

```
npm install
```
```
ng serve
```

Frontend will be available at:

```
http://localhost:4200
```

---

### 4. 🔐 CORS Configuration (Optional)

If you need to allow frontend access from another origin, update in backend:

```
config.setAllowedOrigins(List.of(
  "http://localhost:4200",
  "http://YOUR_IP:4200"
));
```

### Summary

1. Start database (Docker)
2. Run backend (Spring Boot)
3. Run frontend (Angular)

---

## 📁 Project Structure

### Backend

* auth → authentication (login and registration)
* security → JWT and Spring Security configuration
* task → task business logic
* user → user entity
* exception → global error handling
* docker → Docker configuration (PostgreSQL)

### Frontend

* auth → guard, interceptor and auth service
* home → main screen (tasks)
* login / register → authentication screens

---

## Screenshots

### Login


![Login](./docs/login.png)

### Register

![Register](./docs/register.png)

### Home (Tasks)

![Home](./docs/home.png)

---

##  Notes

* Project focused on learning secure backend development
* Stateless authentication using JWT
* Layered architecture (controller, service, repository)
* Simple frontend just for API consumption

- Access tokens have an extended expiration time for simplicity
- Refresh token flow was not implemented in this version
- In production, a short-lived access token + refresh token strategy is recommended

---

## Author

Matheus R.M Silva
