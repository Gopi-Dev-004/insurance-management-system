# 🛡️ Insurance Management System (Spring Boot)

A comprehensive backend **Insurance Management System** developed using **Spring Boot**, designed to handle insurance policies, vehicles, customers, employees, claims, payments, and addons with **JWT-based authentication** and **role-based authorization**.

This project follows **real-world backend architecture** and enterprise-level coding practices.

---

## ⚙️ Feature Implementation

### 🔐 Authentication & Authorization
- Implemented **JWT-based authentication** using Spring Security.
- Generated JWT tokens on successful login and validated them for every secured request.
- Applied **role-based authorization** (Admin, Customer, Employee) at API level.

---

### 👤 User Management
- Customer registration with validation and secure password storage.
- Employee and Admin roles managed with restricted access.
- Role-specific API access enforced using Spring Security.

---

### 🚗 Vehicle & Insurance Management
- Implemented CRUD operations for vehicles, manufacturers, and car models.
- Created insurance policies with addons and pricing configuration.
- Used DTOs to separate request/response models from entities.

---

### 📄 Policy & Payment Processing
- Enabled customers to purchase insurance policies.
- Implemented premium calculation and policy payment flow.
- Stored payment details securely in the database.

---

### 📝 Claim Management
- Customers can raise insurance claims with required details.
- Claims are processed and verified by assigned employees.
- Implemented claim status tracking and pagination support.

---

### 📘 API Documentation (Swagger)
- Integrated **Swagger (OpenAPI)** for interactive API documentation.
- Documented all controllers, request bodies, and response schemas.
- Enabled easy API testing directly from Swagger UI.

---

### 🧱 Architecture & Code Quality
- Followed **layered architecture** (Controller, Service, Repository).
- Implemented **global exception handling** for consistent error responses.
- Used JPA/Hibernate for database interactions with MySQL.
- Applied validations to ensure data integrity.

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
  

### 🔍 Check Versions

```bash
java -version
```
```bash
mvn -version
```


#### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/insurance-management-system.git
```
```bash
cd insurance-management-system
```

#### 2️⃣ Create MySQL Database

- Login to MySQL and create the database:

- CREATE DATABASE insurance_db;



#### 3️⃣ Configure Application Properties


##### Open:

- src/main/resources/application.properties
- Update the following values:

- spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
- spring.datasource.username=root
- spring.datasource.password=yourpassword

- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true

- server.port=8080


##### 📌 Tables will be created automatically when the application starts.

#### 4️⃣ Build the Project

##### Run the following command from the project root:

- mvn clean install
- If build is successful, you are ready to run the application.

#### 5️⃣ Run the Application
##### Option 1: Using Maven
- mvn spring-boot:run
##### Option 2: Using IDE
- Open the main class:

- InsuranceManagementSystemApplication.java
- Right-click → Run

#### 6️⃣ Verify Application is Running
- Once started successfully, you should see logs like:

- Tomcat started on port(s): 8080
- Started InsuranceManagementSystemApplication
- Base URL:

##### http://localhost:8080

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


