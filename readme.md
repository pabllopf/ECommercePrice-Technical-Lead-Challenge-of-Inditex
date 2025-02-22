# ECommercePriceAPI

**ECommercePriceAPI** is a Spring Boot-based application that exposes a RESTful endpoint for querying product prices based on specific date and brand information. This project demonstrates dynamic price retrieval based on time intervals and priority rules using an in-memory H2 database, built with **Java 21**, **Spring Boot 3.4.7**, **OpenAPI**, and **Gravel**.

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

The application follows **Hexagonal Architecture** with a clear separation into layers for easy maintenance and testing. These layers include:

- **Controller Layer**: Exposes the REST API endpoints.
- **Service Layer**: Contains the business logic for price retrieval.
- **Repository Layer**: Manages interactions with the H2 database.
- **Domain Layer**: Defines entities like `Price` and `Product`.

### Diagram

```plaintext
+------------------+         +-----------------+         +--------------------+
|  Controller      | -----> |  Service Layer  | -----> |  Repository Layer  |
|  (REST API)      |         |  (Business Logic)|         |  (Database Access) |
+------------------+         +-----------------+         +--------------------+
```

## API Documentation

### Endpoint: `/api/prices`

#### **GET /api/prices**

**Parameters**:
- `applicationDate` (String, ISO 8601 format): The date and time for which to check the price.
- `productId` (Integer): The product identifier.
- `brandId` (Integer): The brand identifier.

**Response**:
- `productId`: The ID of the product.
- `brandId`: The ID of the brand.
- `priceList`: The price list ID.
- `startDate`: The start date of the price validity.
- `endDate`: The end date of the price validity.
- `price`: The final price to apply.
- `currency`: The currency (e.g., EUR).

Example Request:
```http
GET /api/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
```

Example Response:
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
