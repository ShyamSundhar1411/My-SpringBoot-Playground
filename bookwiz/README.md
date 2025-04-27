# BookWiz 📚

**BookWiz** is a Spring Boot-powered RESTful API designed to manage a personal book library.
It allows users to add, update, delete, and search for books based on different criteria like title, author, or genre.

## Features

- 📖 Add a new book with details like title, author, and genre
- 🛠️ Update existing book information
- 🗑️ Delete books from the library
- 🔍 Search and filter books by title, author, or genre
- 🗂️ View the full list of available books

## Technologies Used

- **Spring Boot** - Main framework for backend development
- **Spring Data JPA** - For database operations and persistence management
- **Lombok** - To reduce boilerplate code (e.g., getters, setters, constructors)
- **H2 Database** - In-memory database for local development and testing
- **Maven** - Build and dependency management tool
- **Springdoc OpenAPI** - API documentation and testing tool for RESTful APIs

## Springdoc OpenAPI Support

Springdoc OpenAPI is integrated into the project for easy interaction and testing of the APIs. Once the application is up and running, you can access the Swagger UI at the following URL:

- Inline Code: `http://localhost:8080/swagger-ui.html`
- Clickable Link: [Swagger UI](http://localhost:8080/swagger-ui.html)

This will provide an interactive documentation of the API endpoints, allowing you to test the APIs directly from the browser.
