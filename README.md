# 🛡️ Insurance Management System (Spring Boot)

A comprehensive backend **Insurance Management System** developed using **Spring Boot**, designed to handle insurance policies, vehicles, customers, employees, claims, payments, and addons with **JWT-based authentication** and **role-based authorization**.

This project follows **real-world backend architecture** and enterprise-level coding practices.

---

## 🚀 Features

- JWT-based authentication and authorization
- Role-based access control (Admin, Customer, Employee)
- Customer registration and KYC management
- Vehicle, manufacturer, and car model management
- Insurance policy creation with addons and pricing
- Claim submission and claim processing workflow
- Payment and policy pricing handling
- Email notification support
- Secure file upload configuration
- Global exception handling and validations
- Clean layered architecture (Controller, Service, Repository)

---

## 👥 User Roles

| Role | Responsibilities |
|-----|------------------|
| Admin | Manage policies, addons, pricing, and employees |
| Customer | Register, manage profile, purchase policies, raise claims |
| Employee | Process and verify assigned insurance claims |

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security (JWT)
- JPA / Hibernate
- MySQL
- Maven
- Swagger (API Documentation) 
- Lombok
- Postman (API Testing)

---

## 🔐 Security Implementation

- JWT token generation on successful login
- Custom JWT filter for request validation
- Role-based endpoint authorization
- Password encryption
- Secure access using Spring Security configuration

---

## 📂 Project Structure

```text

com.insurance.insurancemanagementsystem
│
├── admin
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── auth
│   ├── controller
│   ├── dto
│   └── service
│
├── claim
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── customer
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── employee
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── insurance
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── policy
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── vehicle
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
│
├── payment
│   ├── entity
│   └── repository
│
├── email
│   ├── controller
│   └── service
│
├── common
│   ├── enums
│   ├── exception
│   └── util
│
├── config
│   ├── security
│   │   ├── JwtFilter
│   │   ├── JwtUtil
│   │   ├── CustomUserDetailsService
│   │   └── SecurityConfig
│   └── FileConfig
│
└── InsuranceManagementSystemApplication

```

---

## ▶️ How to Run the Project (Complete Flow)

This section explains how to set up, run, and test the **Insurance Management System** locally.

---

## ✅ Prerequisites

Make sure the following software is installed on your system:

- Java 17 or above  
- Maven  
- MySQL  
- Git  
- IntelliJ IDEA / Eclipse (recommended)  
- Postman (for API testing)

---

Check versions:

java -version
mvn -version
1️⃣ Clone the Repository
git clone https://github.com/your-username/insurance-management-system.git
cd insurance-management-system

2️⃣ Create MySQL Database
Login to MySQL and create the database:

CREATE DATABASE insurance_db;
3️⃣ Configure Application Properties
Open:

src/main/resources/application.properties
Update the following values:

spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

📌 Tables will be created automatically when the application starts.

4️⃣ Build the Project
Run the following command from the project root:

mvn clean install
If build is successful, you are ready to run the application.

5️⃣ Run the Application
Option 1: Using Maven
mvn spring-boot:run
Option 2: Using IDE
Open the main class:

InsuranceManagementSystemApplication.java
Right-click → Run

6️⃣ Verify Application is Running
Once started successfully, you should see logs like:

Tomcat started on port(s): 8080
Started InsuranceManagementSystemApplication
Base URL:

http://localhost:8080

---

## 📘 Swagger API Documentation

This project uses **Swagger UI** to provide interactive API documentation for all REST endpoints.

Swagger helps developers and testers to:
- Explore all available APIs
- View request/response structures
- Test APIs directly from the browser
- Understand request DTOs and response schemas

---

## ▶️ Access Swagger UI

After running the application, open the following URL in your browser:

```text
http://localhost:8080/swagger-ui/index.html

```


