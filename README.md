# 🛡️ Insurance Claim Management System (Spring Boot)

A backend application to manage insurance claims efficiently with secure role-based access using JWT authentication.

---

## 🚀 Features
- User registration & login with JWT authentication
- Role-based access control (Customer, Employee, Admin)
- Insurance policy creation and management
- Claim request submission with file uploads (photos/videos)
- Automatic employee assignment based on claim specialization
- Secure REST APIs with Spring Security
- Exception handling and validation

---

## 🛠️ Tech Stack
- Java 17
- Spring Boot
- Spring Security + JWT
- JPA / Hibernate
- MySQL
- Maven
- Postman (API Testing)

---

## 🧩 System Roles
| Role | Responsibilities |
|-----|------------------|
| Customer | Register, login, submit insurance claims |
| Employee | Process and verify assigned claims |
| Admin | Manage employees, policies, and system configuration |

---

## 🔐 Authentication Flow
1. User logs in using credentials
2. JWT token is generated and returned
3. Token is sent in `Authorization` header for every request
4. Spring Security validates token and role access

---

## 📂 Project Structure
```text
src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── security

## 📸 File Upload Support

Supports multiple file uploads (images/videos)

Files stored securely on server

Media type validation implemented
 ├── exception
 └── config
