# 📝 Blog Application API

This is a **Spring Boot RESTful API** for a blog application, supporting core features such as user registration, authentication, post creation, comment management, and image uploads.

## 🚀 Features

- 🧑 User Registration & Login (JWT Auth)
- 📝 CRUD operations for Posts and Categories
- 💬 Commenting on Posts
- 📷 Upload and manage post images
- 🔒 Role-based access (Admin/User)
- 📁 File handling and image storage

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot
- **Security:** Spring Security, JWT
- **Database:** MySQL / H2 (for testing)
- **Build Tool:** Maven
- **API Docs:** Swagger/OpenAPI

## 📁 Project Structure

```
blog-application-main/
├── blogs-app-api/
│   ├── src/main/java/com/karthik/blog/
│   ├── src/main/resources/
│   ├── images/
│   └── pom.xml
└── README.md
```

## 🧪 Setup & Run Locally

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/blog-application-main.git
   cd blog-application-main/blogs-app-api
   ```

2. **Configure the database**

   Update `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Build and Run**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**

   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
   - Base URL: `http://localhost:8080/api/`

## 🔐 Authentication

Use the `/api/auth/login` endpoint to get a JWT token. Use the token as:

```
Authorization: Bearer <your-token>
```

## 📬 API Endpoints Overview

- `POST /api/auth/register` – Register user
- `POST /api/auth/login` – Login
- `GET /api/posts` – List posts
- `POST /api/posts` – Create a post *(admin/user)*
- `PUT /api/posts/{id}` – Update post
- `DELETE /api/posts/{id}` – Delete post
- `POST /api/posts/{id}/comments` – Add comment
- `POST /api/upload/image` – Upload image

## 👨‍💻 Author

**Karthik Vanabhojana**  
Graduate Student – Northeastern University  
📧 [karthikvanabhojana@gmail.com]
