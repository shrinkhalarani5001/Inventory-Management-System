# 📦 Inventory Management System

A Java Full Stack Inventory Management System built using **Spring Boot, MySQL, Spring Data JPA, Hibernate, and Thymeleaf**.

This application allows users to manage products, monitor inventory levels, search products, and identify low-stock items through a clean and responsive web interface.

---

## 🚀 Features

- ✅ Add new products
- ✅ Edit existing products
- ✅ Delete products
- ✅ Search and filter products
- ✅ Track product quantity
- ✅ Set minimum stock levels
- ✅ Identify low-stock products
- ✅ Calculate total inventory units
- ✅ Calculate total inventory value
- ✅ MySQL database integration
- ✅ Persistent product data using JPA/Hibernate
- ✅ Responsive web interface
- ✅ Server-side rendering with Thymeleaf

---

## 🛠️ Technologies Used

### Backend
- Java 21
- Spring Boot 3.5.5
- Spring MVC
- Spring Data JPA
- Hibernate

### Frontend
- HTML5
- CSS3
- Thymeleaf

### Database
- MySQL 8

### Build Tool
- Maven

### Development Environment
- IntelliJ IDEA

---

## 📂 Project Structure

```text
Inventory Management System/
│
├── Inventory-/
│   ├── pom.xml
│   │
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/example/inventory/
│           │       ├── InventoryManagementApplication.java
│           │       │
│           │       ├── controller/
│           │       │   └── ProductController.java
│           │       │
│           │       ├── model/
│           │       │   └── Product.java
│           │       │
│           │       ├── repository/
│           │       │   └── ProductRepository.java
│           │       │
│           │       └── service/
│           │           └── ProductService.java
│           │
│           └── resources/
│               ├── application.properties
│               │
│               ├── static/
│               │   └── css/
│               │       └── style.css
│               │
│               └── templates/
│                   ├── index.html
│                   └── product-form.html
│
└── README.md
