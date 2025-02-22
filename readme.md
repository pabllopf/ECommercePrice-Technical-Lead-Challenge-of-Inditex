# ECommercePriceAPI

**ECommercePriceAPI** is a Spring Boot-based application that exposes a RESTful endpoint for querying product prices based on specific date and brand information. This project demonstrates dynamic price retrieval based on time intervals and priority rules using an in-memory H2 database, built with **Java 21**, **Spring Boot 3.4.7**, **OpenAPI**, and **Gravel**.

## Useful Links  

| Description                                    | Link |
|------------------------------------------------|------|
| **Swagger UI (API documentation and testing)** | [Swagger UI](https://inditex-bcnc-ecommerce-price-engine.onrender.com/swagger-ui/index.html) |
| **Render Dashboard (Deployment and monitoring)** | [Render Dashboard](https://dashboard.render.com) |
| **H2 Console (In-memory database exploration)** | [H2 Console](https://inditex-bcnc-ecommerce-price-engine.onrender.com/h2-console) |
| **Test Reports (Automated test results)** | [Test Reports](https://inditex-bcnc-ecommerce-price-engine.onrender.com/test-reports/index.html) |
| **SonarCloud (Code quality and analysis)** | [SonarCloud](https://sonarcloud.io/summary/overall?id=pabllopf_ECommercePrice-Technical-Lead-Challenge-of-Inditex-BCNC-Group&branch=master) |  

## Table of Contents
0. [Quick Start](#quick-start)  
1. [Introduction](#introduction)  
2. [Features](#features)  
3. [Technologies](#technologies)  
4. [Architecture](#architecture)  
5. [API Documentation](#api-documentation)  
6. [Test Scenarios](#test-scenarios)  
7. [Setup and Installation](#setup-and-installation)  
8. [CI/CD](#cicd)  
9. [Contributing](#contributing)  
10. [License](#license)

## Quick Start

> **Warning:**  
> If the service is not active immediately, you may encounter a **502 Bad Gateway** error. Please wait up to 3 minutes for the service to automatically restart. This is due to Render's free tier deactivating services after 15 minutes of inactivity. The service should come back online automatically after that.

## How to Test and Use the Inditex Ecommerce Price Engine API

### Introduction
The Inditex Ecommerce Price Engine API allows you to interact with product pricing data available on the Inditex e-commerce platform. You can access and test the API through the Swagger UI interface, which provides an interactive environment for documentation and testing.

### How to Access the API

1. Open your web browser and navigate to the Swagger UI documentation at:  
   [Inditex Ecommerce Price Engine Swagger UI](https://inditex-bcnc-ecommerce-price-engine.onrender.com/swagger-ui/index.html)

2. Upon opening the link, the interactive interface will display available endpoints and their descriptions.

### How to Test the API

1. **Explore Available Endpoints:**
   The Swagger UI lists all API endpoints along with their HTTP methods (GET, POST, etc.). Expand each endpoint to view more details, such as request parameters and response formats.

2. **Test an Endpoint:**
   - Select the desired endpoint.
   - Click “Try it out” to enable input fields.
   - Enter required parameters (if any).
   - Click “Execute” to send the request.
   - The response will display, including the returned data, status code, and any errors.

3. **View Response:**
   After executing a request, you can see the API response directly in the Swagger UI, including the body (in JSON or XML), headers, and the HTTP status code.

### Platform Used to Deploy

The Inditex Ecommerce Price Engine API is deployed on **Render**, a platform for web app and API deployment. Render supports various backends and offers easy scaling.

More info:  
[Render Dashboard](https://dashboard.render.com)

### Conclusion

Swagger UI provides an intuitive way to explore and test the API without needing to write code. It allows interaction with the API, testing endpoints, and viewing real-time results.

## Introduction

The application provides a REST endpoint to query product prices for a given brand, product ID, and application date. The system retrieves prices from an H2 in-memory database, which contains prices for various time intervals and brand-specific priorities.

Example data in the database:

| BRAND_ID | START_DATE | END_DATE | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|------------|----------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4 | 35455 | 1 | 38.95 | EUR |


## Access the H2 Database

The project uses an in-memory H2 database to store prices and product information. You can access the H2 admin panel to explore and run queries directly on the database.

### How to Access the H2 Panel

1. **Access the H2 Panel**:  
   The H2 panel is available at the following URL when the application is running:
   
   [H2 Console](https://inditex-bcnc-ecommerce-price-engine.onrender.com/h2-console)

2. **Configure the Connection**:  
   Upon opening the URL, you will see a login screen for the H2 console. Use the following settings to connect to the in-memory database:

   - **Driver Class**: `org.h2.Driver`
   - **JDBC URL**: `jdbc:h2:mem:ecommerceprice`
   - **User Name**: `sa`
   - **Password**: (leave empty)

3. **Explore the Database**:  
   Once logged in, you can run SQL queries on the database tables. You can query the price data, products, and perform operations directly on the test database.

### Example Query

To view the available prices for a product, you can run a query like:

```sql
SELECT * FROM PRICE;
```

This will return all rows from the `PRICE` table, which contains pricing details, validity dates, and other relevant information.

### Important Notes

- The H2 database used is **in-memory**, meaning the data will be lost when the application is stopped.
- The H2 console is mainly for **development** and **testing environments**.


## Features

- **REST API Endpoint**: Accepts product ID, brand ID, and a date, returning relevant pricing information.
- **In-Memory Database (H2)**: Lightweight database for fast price queries.
- **Dynamic Pricing**: Prices are fetched dynamically based on the requested date, product, and brand.
- **Price Priority**: When two prices overlap, the one with the higher priority is returned.
- **Error Handling**: Centralized exception handling via `@ControllerAdvice`, returning appropriate HTTP status codes.

## Technologies

- **Java 21**: Latest Java version for improved performance and language features.
- **Spring Boot 3.4.7**: Framework for building RESTful APIs.
- **H2 Database**: In-memory database to store price data.
- **OpenAPI**: API documentation standard for describing the RESTful service.
- **Gravel**: Used for API monitoring and documentation enhancements.
- **JUnit 5 & Mockito**: For unit testing and mocking dependencies.
- **Jacoco**: Code coverage reporting.
- **Gradle**: Build automation and dependency management.
- **Lombok**: Reduces boilerplate code (getters, setters, constructors).
- **ControllerAdvice**: Centralized exception handling.


## Architecture

The application follows **Hexagonal Architecture (Ports & Adapters)**, ensuring a clear separation of concerns, better maintainability, and easy testing. The architecture is structured into three main layers:

### **1. Application Layer**
This layer orchestrates the business logic and acts as a bridge between external interactions and the domain layer. It consists of:

- **DTOs (Data Transfer Objects):** Structures used to transfer data between layers while maintaining encapsulation.
- **Filters:** Middleware components that handle cross-cutting concerns like request validation, logging, or authentication.
- **Services:** Implements the business logic related to price retrieval and other core functionalities.
- **Use Cases:** Defines application-specific business rules and workflows.

### **2. Domain Layer**
The **core of the application**, where business logic is defined independently of any external frameworks. It includes:

- **Model:** Contains the main business entities such as `Price` and `Product`, ensuring a clear domain model.
- **Ports:** Defines interfaces (inbound and outbound) that abstract the interaction between the application and external dependencies.

### **3. Infrastructure Layer**
Handles all external concerns such as API exposure, data persistence, configuration, and exception handling. It consists of:

- **Adapters:** Implements the port interfaces, allowing communication with external services or databases.
- **Config:** Manages application-wide configurations such as dependency injection and environment variables.
- **Controllers:** Exposes REST API endpoints to interact with the application.
- **Entities:** Represents database models mapped to the domain.
- **Exceptions:** Handles and standardizes error responses.
- **Repositories:** Implements data access logic, interacting with the database.

### **Why Hexagonal Architecture?**
The **Ports & Adapters** approach ensures:
- **Decoupling:** Business logic is independent of frameworks, databases, or UI.
- **Testability:** Core logic can be tested without dependencies.
- **Flexibility:** Easy to replace or extend components (e.g., change from H2 to another database).
- **Maintainability:** Clear boundaries between application layers simplify modifications.

### **Diagram Representation**
```plaintext
+------------------+         +-----------------+         +--------------------+
|  Controllers     | -----> |  Service Layer  | -----> |  Repositories      |
|  (REST API)      |         |  (Business Logic)|         |  (Database Access) |
+------------------+         +-----------------+         +--------------------+
          |                          |                             |
          |                          |                             |
          v                          v                             v
  +----------------+       +------------------+       +-------------------+
  |  Filters       | ----> |  Use Cases       | ----> |  Infrastructure   |
  +----------------+       +------------------+       +-------------------+
                                  |
                                  v
                     +----------------------+
                     |  Domain Layer (Core)  |
                     +----------------------+
                     |  Models & Ports      |
                     +----------------------+
```

This architecture ensures a **scalable, testable, and maintainable** application, making it adaptable to future changes. 🚀

## API Documentation

### Base URL
`https://inditex-bcnc-ecommerce-price-engine.onrender.com`

### **1. Get Price by Application Date**
**Endpoint:** `GET /api/prices`

**Description:**
Retrieves the applicable price for a given product, brand, and application date.

**Parameters:**
- `applicationDate` (String, ISO 8601 format) - Required
- `productId` (Integer) - Required
- `brandId` (Integer) - Required

**Response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

---

### **2. Get Price by ID**
**Endpoint:** `GET /api/prices/{id}`

**Description:**
Retrieves the price details by its unique ID.

**Parameters:**
- `id` (Integer, path parameter) - Required

**Response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

---

### **3. Create a New Price**
**Endpoint:** `POST /api/prices`

**Description:**
Creates a new price entry for a product.

**Request Body:**
```json
{
  "brandId": 1,
  "productId": 35455,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priority": 0,
  "price": 35.5,
  "currency": "EUR"
}
```

**Response:**
- `201 Created` if the price entry is successfully created.

---

### **4. Update an Existing Price**
**Endpoint:** `PUT /api/prices`

**Description:**
Updates an existing price entry for a product.

**Request Body:**
```json
{
  "id": 1,
  "brandId": 1,
  "productId": 35455,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "priority": 1,
  "price": 25.45,
  "currency": "EUR"
}
```

**Response:**
- `200 OK` if the price is updated successfully.
- `404 Not Found` if the price entry does not exist.

---

### **5. Delete a Price**
**Endpoint:** `DELETE /api/prices/{id}`

**Description:**
Deletes a price entry by its ID.

**Parameters:**
- `id` (Integer, path parameter) - Required

**Response:**
- `200 OK` if the price is deleted successfully.
- `404 Not Found` if the price entry does not exist.

---

### **6. Retrieve All Prices**
**Endpoint:** `GET /api/prices/all`

**Description:**
Fetches all available prices in the system.

**Response:**
```json
[
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
  }
]
```

---

### **7. Authentication**

#### **User Login**
**Endpoint:** `POST /api/auth/login`

**Description:**
Authenticates a user and returns a JWT token.

**Request Body:**
```json
{
  "username": "user1",
  "password": "user1"
}
```

**Response:**
- `200 OK` with a JWT token on successful authentication.
- `403 Forbidden` if authentication fails.

---

### **Security**
- All endpoints require authentication via JWT, except `GET /api/prices` and `GET /api/prices/{id}`.
- Include the `Authorization: Bearer <token>` header in protected requests.


## Test Scenarios

1. **Test 1**: Request at 10:00 on June 14 for product 35455 and brand 1 (ZARA).
2. **Test 2**: Request at 16:00 on June 14 for product 35455 and brand 1 (ZARA).
3. **Test 3**: Request at 21:00 on June 14 for product 35455 and brand 1 (ZARA).
4. **Test 4**: Request at 10:00 on June 15 for product 35455 and brand 1 (ZARA).
5. **Test 5**: Request at 21:00 on June 16 for product 35455 and brand 1 (ZARA).
6. **Integration Test**: Ensure the integration between controller, service, and repository layers.

## Setup and Installation

### Prerequisites
- **Java 21** or higher
- **Gradle**
- IDE (e.g., IntelliJ IDEA or Eclipse)

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/ECommercePriceAPI.git
    ```

2. Navigate to the project folder:
    ```bash
    cd ECommercePriceAPI
    ```

3. Build and run the application:
    ```bash
    gradle bootRun
    ```

4. The application should now be running at `http://localhost:8080`.

### Running Tests
To run the tests, use the following Gradle command:
```bash
gradle test
```

### Unit Tests Overview

Unit tests are an essential part of this project, ensuring that the core components of the application, including the controller, service, and repository layers, function correctly. This application uses **JUnit** and **Mockito** for unit testing and mocking external dependencies. Unit tests are written to verify the behavior of individual components in isolation.

### Testing Strategy

- **Controller Layer Tests**: Verifies the correct behavior of REST API endpoints, including handling different input parameters and status codes.
- **Service Layer Tests**: Focuses on the business logic, ensuring correct price retrieval and priority rules.
- **Repository Layer Tests**: Ensures data retrieval from the H2 database is accurate and efficient.


This will run all unit tests defined in the project and generate a test report.

### Test Report

You can view the test results in a detailed HTML report. The test report is generated after the tests are executed and can be accessed via the following URL:

- [Test Reports](https://inditex-bcnc-ecommerce-price-engine.onrender.com/test-reports/index.html)

This page provides an overview of the test execution, including the number of tests run, passed, failed, and skipped, along with detailed information for each test case.

## CI/CD

This project is integrated with **CI/CD** to ensure automated builds and tests. The pipeline is configured with **GitHub Actions**.

### Example GitHub Actions Workflow
```yaml
name: Java CI with Gradle

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Check out code
      uses: actions/checkout@v2

    - name: Set up JDK 21
      uses: actions/setup-java@v2
      with:
        java-version: '21'
        distribution: 'adoptopenjdk'

    - name: Build with Gradle
      run: gradle clean build

    - name: Run Tests
      run: gradle test
```

## Contributing

Feel free to fork the repository and submit issues or pull requests. Ensure you follow coding standards and write meaningful commit messages.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Live Deployment
- [Swagger UI](https://inditex-bcnc-ecommerce-price-engine.onrender.com/swagger-ui/index.html)
- [H2 Database Console](https://inditex-bcnc-ecommerce-price-engine.onrender.com/h2-console)
- [API Documentation](https://inditex-bcnc-ecommerce-price-engine.onrender.com/v3/api-docs)
- [SonarCloud Project](https://sonarcloud.io/project/information?id=pabllopf_ECommercePrice-Technical-Lead-Challenge-of-Inditex-BCNC-Group)
