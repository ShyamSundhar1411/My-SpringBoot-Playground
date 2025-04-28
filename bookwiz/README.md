
# BookWiz 📚

**BookWiz** is a **Spring Boot**-powered RESTful API designed to manage a **personal book library**.  
It allows users to **add**, **update**, **delete**, **bulk create**, and **search** for books based on multiple criteria like title, author, publisher, or genre.

---

## 🚀 Features

- 📖 **Add** a new book with details like title, author, genre, and publisher
- 🛠️ **Update** existing book information
- 🗑️ **Delete** books from the library
- 🔍 **Search** books by title, author, or publisher (case-insensitive, partial match)
- 📚 **View** the full list of available books
- 🛒 **Bulk Create Books** in one API call (skips duplicates automatically)

---

## 🛠️ Technologies Used

- **Spring Boot** – Main framework for backend development
- **Spring Data JPA** – For database operations and persistence management
- **Lombok** – Reduces boilerplate code (e.g., getters, setters, constructors)
- **H2 Database** – In-memory database for local development and testing
- **Maven** – Build and dependency management tool
- **Springdoc OpenAPI** – For generating interactive API documentation

---

## 📜 API Documentation (Swagger UI)

Springdoc OpenAPI is integrated for easy testing and documentation.

- 🔗 Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

You can test all API endpoints directly from the browser!

---

## 📚 Available API Endpoints

| Method  | Endpoint                        | Description                                   |
|---------|----------------------------------|-----------------------------------------------|
| `GET`   | `/api/v1/books`                  | Fetch all books                              |
| `GET`   | `/api/v1/books/{bookId}`          | Fetch a book by its ID                       |
| `POST`  | `/api/v1/books/create`            | Create a new book                            |
| `PUT`   | `/api/v1/books/{bookId}`          | Update an existing book                     |
| `DELETE`| `/api/v1/books/{bookId}`          | Delete a book                                |
| `POST`  | `/api/v1/books/create/bulk`       | Bulk create books (skips duplicates)          |
| `GET`   | `/api/v1/search?q={searchText}`   | Search books by title, author, or publisher  |

---

## 🔍 Example Search Usage

To **search** for books by a text query:

```http
GET /api/v1/search?q=tolkien
```

✅ This will return all books where:
- The **title**, **author**, or **publisher** contains "tolkien" (case-insensitive).

---

## 🛒 Example Bulk Create Usage

To **bulk create books** in a single request:

```http
POST /api/v1/books/create/bulk
```

**Request Body** (JSON Array):

```json
[
  {
    "bookTitle": "Atomic Habits",
    "author": "James Clear",
    "genre": "Self-Help",
    "publishedBy": "Penguin"
  },
  {
    "bookTitle": "The Hobbit",
    "author": "J.R.R. Tolkien",
    "genre": "Fantasy",
    "publishedBy": "Allen & Unwin"
  }
]
```

✅ The API will **create all new books**.  
🚫 **Duplicate books** (based on **title and author**) will be **automatically skipped** without an error.

