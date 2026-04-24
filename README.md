# EcommerceApi - Flower Bouquet E-Commerce REST API

A RESTful API backend for an e-commerce flower shop built with Spring Boot. This project demonstrates HTTP fundamentals, REST principles, and CRUD operations using in-memory data storage.

## Project Overview

This API provides a complete product catalog management system for a flower bouquet shop. It supports creating, reading, updating, and deleting products, along with filtering capabilities by name, category, and price.

**Built with:**
- Java 26
- Spring Boot 4.0.5
- Gradle
- Lombok

## Setup Instructions

### Prerequisites
- Java 21 or higher installed on your system
- Git

### How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/nekowo33/EcommerceApi.git
   cd EcommerceApi
   ```

2. Run the application using the Gradle wrapper:
   ```bash
   # On Windows
   .\EcommerceApi\gradlew.bat bootRun -p EcommerceApi

   # On macOS/Linux
   ./EcommerceApi/gradlew bootRun -p EcommerceApi
   ```

3. The API will be available at `http://localhost:8080`

## API Endpoint Reference

Base URL: `http://localhost:8080/api/v1/products`

| Method   | Endpoint                          | Description                        | Status Codes          |
|----------|-----------------------------------|------------------------------------|-----------------------|
| `GET`    | `/api/v1/products`                | Retrieve all products              | 200 OK                |
| `GET`    | `/api/v1/products/{id}`           | Retrieve a product by ID           | 200 OK, 404 Not Found |
| `GET`    | `/api/v1/products/filter`         | Filter products by criteria        | 200 OK                |
| `POST`   | `/api/v1/products`                | Create a new product               | 201 Created, 400 Bad Request |
| `PUT`    | `/api/v1/products/{id}`           | Replace an entire product          | 200 OK, 404 Not Found |
| `PATCH`  | `/api/v1/products/{id}`           | Partially update a product         | 200 OK, 404 Not Found |
| `DELETE` | `/api/v1/products/{id}`           | Delete a product                   | 204 No Content, 404 Not Found |

### Filter Parameters

The filter endpoint accepts the following query parameters:

| Parameter     | Type   | Description                                          |
|---------------|--------|------------------------------------------------------|
| `filterType`  | String | The criteria to filter by: `name`, `category`, `price` |
| `filterValue` | String | The value to match against the filter type           |

## Sample Requests and Responses

### GET All Products
```
GET http://localhost:8080/api/v1/products
```
**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Roses Bouquet",
    "description": "A classic bouquet of fresh red roses.",
    "price": 1200.0,
    "category": "Flowers",
    "stockQuantity": 50,
    "imageUrl": "https://i.pinimg.com/736x/5a/aa/d2/5aaad243a6cc3e7e3c1f6669b50b260d.jpg"
  }
]
```

### GET Product by ID
```
GET http://localhost:8080/api/v1/products/1
```
**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Roses Bouquet",
  "description": "A classic bouquet of fresh red roses.",
  "price": 1200.0,
  "category": "Flowers",
  "stockQuantity": 50,
  "imageUrl": "https://i.pinimg.com/736x/5a/aa/d2/5aaad243a6cc3e7e3c1f6669b50b260d.jpg"
}
```

### Filter Products by Category
```
GET http://localhost:8080/api/v1/products/filter?filterType=category&filterValue=Flowers
```

### Filter Products by Name
```
GET http://localhost:8080/api/v1/products/filter?filterType=name&filterValue=Rose
```

### Filter Products by Price (max price)
```
GET http://localhost:8080/api/v1/products/filter?filterType=price&filterValue=1500
```

### POST Create a Product
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "Cherry Blossom Bouquet",
  "description": "A seasonal cherry blossom arrangement.",
  "price": 2800.00,
  "category": "Flowers",
  "stockQuantity": 15,
  "imageUrl": "https://example.com/cherry.jpg"
}
```
**Response (201 Created):**
```json
{
  "id": 11,
  "name": "Cherry Blossom Bouquet",
  "description": "A seasonal cherry blossom arrangement.",
  "price": 2800.0,
  "category": "Flowers",
  "stockQuantity": 15,
  "imageUrl": "https://example.com/cherry.jpg"
}
```

### PUT Update a Product
```
PUT http://localhost:8080/api/v1/products/1
Content-Type: application/json

{
  "name": "Premium Roses Bouquet",
  "description": "A luxury bouquet of premium red roses.",
  "price": 2500.00,
  "category": "Premium Flowers",
  "stockQuantity": 20,
  "imageUrl": "https://example.com/premium-roses.jpg"
}
```
**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Premium Roses Bouquet",
  "description": "A luxury bouquet of premium red roses.",
  "price": 2500.0,
  "category": "Premium Flowers",
  "stockQuantity": 20,
  "imageUrl": "https://example.com/premium-roses.jpg"
}
```

### PATCH Partially Update a Product
```
PATCH http://localhost:8080/api/v1/products/2
Content-Type: application/json

{
  "price": 1600.00
}
```
**Response (200 OK):**
```json
{
  "id": 2,
  "name": "Tulips Bouquet",
  "description": "A beautiful bouquet of fresh tulips.",
  "price": 1600.0,
  "category": "Flowers",
  "stockQuantity": 40,
  "imageUrl": "https://i.pinimg.com/736x/f8/6c/7b/f86c7b3f61c7b5409d462498f05bb11b.jpg"
}
```

### DELETE a Product
```
DELETE http://localhost:8080/api/v1/products/1
```
**Response: 204 No Content**

### Error Response (404 Not Found)
```
GET http://localhost:8080/api/v1/products/999
```
**Response (404 Not Found):**
```json
{
  "timestamp": "2026-04-24T14:14:44.460616",
  "status": 404,
  "error": "Not Found",
  "message": "Product with ID 999 not found."
}
```

### Error Response (400 Bad Request)
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "",
  "price": -50,
  "category": ""
}
```
**Response (400 Bad Request):**
```json
{
  "timestamp": "2026-04-24T14:14:53.390",
  "status": 400,
  "error": "Bad Request",
  "message": "Product name is required and must be at least 2 characters."
}
```

## HTTP Status Codes Used

| Status Code            | When Used                                      |
|------------------------|-------------------------------------------------|
| `200 OK`               | Successful GET, PUT, or PATCH request           |
| `201 Created`          | Successfully created a new product via POST     |
| `204 No Content`       | Successfully deleted a product via DELETE        |
| `400 Bad Request`      | Invalid input data (missing name, negative price, etc.) |
| `404 Not Found`        | Product with the specified ID does not exist     |
| `500 Internal Server Error` | Unexpected server-side error               |

## Input Validation Rules

| Field          | Rule                              |
|----------------|-----------------------------------|
| `name`         | Required, minimum 2 characters    |
| `price`        | Must be a positive number         |
| `category`     | Required, cannot be empty         |
| `stockQuantity`| Must be non-negative (0 or more)  |

## Known Limitations

- **In-memory storage**: All product data is stored in a `List<Product>` and will be lost when the application is restarted. There is no database persistence.
- **ID generation**: Uses a simple counter (`idCounter++`). Deleted IDs are not reused.
- **No authentication**: The API does not implement any authentication or authorization.
- **Single-threaded concerns**: The in-memory list is not thread-safe for concurrent access.

## Project Structure

```
EcommerceApi/
└── src/main/java/com/ws101/novio/EcommerceApi/
    ├── EcommerceApiApplication.java       # Main Spring Boot application
    ├── controller/
    │   └── ProductController.java         # REST API endpoint handlers
    ├── service/
    │   └── ProductService.java            # Business logic and data storage
    ├── model/
    │   └── Product.java                   # Product entity definition
    └── exception/
        ├── GlobalExceptionHandler.java    # Centralized error handling
        ├── ErrorResponse.java             # Error response DTO
        ├── ProductNotFoundException.java  # 404 exception
        └── InvalidInputException.java     # 400 exception
```

## Authors

- **Novio, Mariel Kimberly B.** - Product model, service layer, and sample data
- **Cosino, Vivian Faith C.** - REST controller, error handling, validation, and documentation
