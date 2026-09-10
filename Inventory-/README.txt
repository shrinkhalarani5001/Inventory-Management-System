INVENTORY MANAGEMENT SYSTEM - JAVA FULL STACK
================================================

TECHNOLOGY
----------
Backend:
- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

Frontend:
- HTML
- CSS
- Thymeleaf

Database:
- MySQL

BUILD:
- Maven

IMPORTANT: DIFFERENT RUN METHOD
--------------------------------
You do NOT need to run:
    mvn spring-boot:run

Instead, run the Java application directly from IntelliJ IDEA or Eclipse.

INTELLIJ IDEA METHOD
--------------------
1. Install JDK 17.
2. Install MySQL.
3. Create the database:

   CREATE DATABASE inventory_db;

4. Open this project in IntelliJ IDEA.
5. Open:
   src/main/resources/application.properties
6. Change:

   spring.datasource.password=YOUR_MYSQL_PASSWORD

   to your actual MySQL root password.

7. Open:
   src/main/java/com/example/inventory/InventoryManagementApplication.java
8. Click the green RUN button beside the main() method.

EXPECTED CONSOLE OUTPUT
-----------------------
You should see something similar to:

Started InventoryManagementApplication in ... seconds

Then open:

http://localhost:8080

EXPECTED APPLICATION
--------------------
Dashboard with:
- Total Products
- Total Units
- Low Stock
- Inventory Value
- Product table
- Add Product
- Edit Product
- Delete Product
- Search
- Stock filter

SAMPLE DATA
-----------
The application automatically inserts 4 sample products into an empty database.

MYSQL
-----
Database name:
inventory_db

Table:
products

The table is automatically created/updated by Hibernate because:

spring.jpa.hibernate.ddl-auto=update

NO MAVEN TERMINAL COMMAND
-------------------------
For this version, use the IDE Run button to start the Spring Boot application.
This avoids the previous Maven-terminal startup approach.
