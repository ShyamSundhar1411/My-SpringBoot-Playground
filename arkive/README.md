
# ☁️🗄️ Arkive

**Arkive** is a secure, Spring Boot-powered file storage system integrated with AWS S3. It allows users to upload, download, and manage files while securely storing metadata. With JWT authentication and scalable cloud storage, Arkive makes file management efficient and user-friendly.

---

## 🚀 Features

- 🗂️ Upload files to AWS S3
- 📥 Download and retrieve file metadata
- 🗑️ Delete files securely
- 🔒 JWT-based Authentication
- 🌐 RESTful APIs with Swagger documentation
- 📦 Clean Architecture using DTOs and Services

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **AWS S3 SDK**
- **Lombok**
- **Jakarta Validation**
- **Springdoc OpenAPI**
- **Maven**

---

## 📚 API Endpoints

| Method   | Endpoint                              | Description                                        |
|----------|---------------------------------------|----------------------------------------------------|
| `POST`   | `/api/v1/file/upload`                 | Upload a file to S3 and return file metadata       |
| `GET`    | `/api/v1/file/files/`                 | Get all uploaded files for the authenticated user  |
| `GET`    | `/api/v1/file/files/{fileId}`         | Get specific file metadata by file ID              |
| `DELETE` | `/api/v1/file/files/{fileId}/delete/` | Delete a file by file ID                           |

> All routes require a valid JWT Bearer token unless specified otherwise.

---

## 🧰 API Documentation

Once the application is running locally, navigate to:

```
http://localhost:8080/swagger-ui/index.html
```

Use the **Authorize** button to input your Bearer token before interacting with protected endpoints.

---

## 📄 License

This project is licensed under the MIT License.
