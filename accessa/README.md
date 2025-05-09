# 🔐 Accessa

Accessa is a secure and scalable user authentication and profile management API built with **Spring Boot 3**, designed for modern applications that require clean architecture, JWT-based authentication, and robust user session control.

---

## 🚀 Features

- ✅ User Registration & Login
- 🔁 Token Refresh with Access/Refresh JWTs
- 👤 Profile View, Update, and Delete
- 🧼 Clean Architecture with DTOs and Service Interfaces
- 📄 Swagger/OpenAPI documentation
- 🔒 Secure endpoints with Bearer Auth

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security with JWT**
- **Lombok**
- **Jakarta Validation**
- **Swagger / OpenAPI 3**
- **Maven**

---

## 📚 API Endpoints

| Method   | Endpoint                  | Description                                  |
|----------|---------------------------|----------------------------------------------|
| `POST`   | `/api/v1/auth/register`   | Register a new user                          |
| `POST`   | `/api/v1/auth/login`      | Login user and receive access/refresh tokens |
| `POST`   | `/api/v1/token/refresh`   | Refresh JWT access token                     |
| `GET`    | `/api/v1/users/me`        | Fetch current user profile                   |
| `PUT`    | `/api/v1/users/me`        | Update current user profile                  |
| `DELETE` | `/api/v1/users/me`        | Delete current user account                  |

> All user-related routes (except register & login) require a valid JWT Bearer token.


## 🧰 API Documentation

Once running, navigate to:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 👨‍💻 Author

**Shyam Sundhar**  
[GitHub](https://github.com/ShyamSundhar1411) • [LinkedIn](https://linkedin.com/in/shyamsundhar2435)

---

## 📄 License

This project is licensed under the MIT License.
