# ECommercePriceAPI

**ECommercePriceAPI** is a Spring Boot-based application that exposes a RESTful endpoint for querying product prices based on specific date and brand information. This project aims to demonstrate the ability to handle dynamic price retrieval based on time intervals and priority rules using an in-memory H2 database.

## Table of Contents

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

## Introduction

The application provides a REST endpoint to query the product price for a given brand, product ID, and application date. The system retrieves the price from a sample database (H2) that includes prices for different time intervals and brand-specific priorities.

Example data provided in the database:

| BRAND_ID | START_DATE | END_DATE | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|------------|----------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1 | 35455 | 0 | 35.50 | EUR |
| 1        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2 | 35455 | 1 | 25.45 | EUR |
| 1        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3 | 35455 | 1 | 30.50 | EUR |
| 1        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4 | 35455 | 1 | 38.95 | EUR |

## Features

- **REST API Endpoint**: Accepts product ID, brand ID, and a date and returns the relevant pricing information.
- **In-Memory Database (H2)**: The pricing data is stored in a lightweight in-memory H2 database for efficient querying.
- **Dynamic Pricing**: Fetches prices dynamically based on the requested date, product, and brand.
- **Price Priority**: If two pricing entries overlap, the one with the highest priority (numerically) is returned.
- **Error Handling**: Uses controller advice to handle exceptions and return appropriate HTTP status codes.

## Technologies

- **Spring Boot**: For building the RESTful web service.
- **H2 Database**: In-memory database for storing price data.
- **JUnit & Mockito**: For unit testing and mocking dependencies.
- **Jacoco**: For code coverage reporting.
- **Gradle**: For project management and dependencies.
- **Lombok**: To reduce boilerplate code (getters, setters, constructors, etc.).
- **ControllerAdvice**: For centralized exception handling.

## Architecture

The application follows the **Hexagonal Architecture** with a clear separation of concerns into layers, allowing for easy maintenance, testing, and extension. The layers are as follows:

- **Controller Layer**: Exposes the REST API endpoints.
- **Service Layer**: Contains business logic for price retrieval.
- **Repository Layer**: Interfaces with the H2 database to retrieve pricing data.
- **Domain Layer**: Contains entities like `Price` and `Product`.

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
6. **Integration Test**: Ensure the integration between the controller, service, and repository layers works correctly.

## Setup and Installation

### Prerequisites
- Java 11 or higher
- Gradle
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

4. The application should be running at `http://localhost:8080`.

### Running Tests
To run the tests, use the following Gradle command:
```bash
gradle test
```

## CI/CD

This project includes CI/CD integration to ensure automated builds and tests. The pipeline is set up in GitHub Actions.

### Example Workflow (GitHub Actions)
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

    - name: Set up JDK 11
      uses: actions/setup-java@v2
      with:
        java-version: '11'
        distribution: 'adoptopenjdk'

    - name: Build with Gradle
      run: gradle clean build

    - name: Run Tests
      run: gradle test
```

## Contributing

Feel free to fork the repository and submit issues or pull requests. Ensure you follow the coding standards and write meaningful commit messages.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Live Deployment
- [Swagger UI](https://inditex-bcnc-ecommerce-price-engine.onrender.com/swagger-ui/index.html)
- [H2 Database Console](https://inditex-bcnc-ecommerce-price-engine.onrender.com/h2-console)
- [API Documentation](https://inditex-bcnc-ecommerce-price-engine.onrender.com/v3/api-docs)
- [SonarCloud Project](https://sonarcloud.io/project/information?id=pabllopf_ECommercePrice-Technical-Lead-Challenge-of-Inditex-BCNC-Group)
