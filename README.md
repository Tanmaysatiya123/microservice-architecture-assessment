# Microservice Application (User Service & Order Service)

## 🔹 Project Overview
This application comprises **two Spring Boot microservice modules**:

- **User Service:** Manages User CRUD operations.
- **Order Service:** Handles Order CRUD operations and validates User IDs against User Service.

The services use **H2 in-memory databases** for simplicity and demonstration.

---

## 🔹 Tech Stack
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **JUnit + Mockito for Tests**
- **Maven**
  
---

## 🔹 Architectural Components

microservice/
├─ user-service/
| ├─ UserController, UserService, UserRepository
| ├─ UserNotFoundException, GlobalExceptionHandler
└─ order-service/
├─ OrderController, OrderService, OrderRepository
├─ UserClient (to validate User IDs against User service)
├─ UserValidationException, GlobalExceptionHandler


---

## 🔹 Exception Handling
Custom Exception handlers have been implemented in each service:
- **UserNotFoundException**
- **UserValidationException**
- **OrderNotFoundException**

Controllers respond with appropriate **HTTP statuses and messages**.

---

## 🔹 Installation and Run instructions

```shell
git clone <your-repo-url>
cd microservice

# Build both modules
mvn clean install

# Run User Service
cd user-service
mvn spring-boot:run

# Run Order Service
cd order-service
mvn spring-boot:run


🔹 API Endpoints
Method	URL	Description
User	POST	/users	Create User
User	GET	/users/{id}	Retrieve User
User	PUT	/users/{id}	Update User
User	DELETE	/users/{id}	Delete User
Order	POST	/orders	Create Order
Order	GET	/orders/{id}	Retrieve Order
Order	PUT	/orders/{id}	Update Order
Order	DELETE	/orders/{id}	Delete Order