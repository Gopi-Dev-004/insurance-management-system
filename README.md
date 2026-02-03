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
