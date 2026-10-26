# Employee Leave Management System
## API Routes and Code Annotations — Practical Reference

This file explains the API routes and important annotations used in the Employee Leave Management System. The code snippets are illustrative examples showing where comments belong; they are not complete replacements for your existing controller files.

---

## 1. `EmployeeController.java`

```java
@RestController
// Marks this class as a REST API controller.
// Methods return response data, usually JSON, to the frontend.

@RequestMapping("/api/employees")
// Common URL prefix for all employee APIs.

@CrossOrigin(origins = "http://localhost:5173")
// Allows requests from the Vue frontend running on port 5173.

public class EmployeeController {

    @PostMapping
    // POST /api/employees
    // Creates and saves a new employee.

    @GetMapping
    // GET /api/employees
    // Retrieves all employees from MongoDB.
}
```

### What the routes do
- `POST /api/employees`: accepts employee details and saves a new employee.
- `GET /api/employees`: returns the employee records.

Example employee request body:

```json
{
  "name": "Arun",
  "employeeCode": "EMP101",
  "department": "CSE",
  "email": "arun@example.com",
  "totalLeaves": 20
}
```

---

## 2. `LeaveController.java`

```java
@RestController
// Handles HTTP requests related to leave management.

@RequestMapping("/api/leaves")
// Common URL prefix for leave APIs.

@CrossOrigin(origins = "http://localhost:5173")
// Allows the Vue frontend to access these APIs.

public class LeaveController {

    @PostMapping
    // POST /api/leaves
    // Applies for leave and checks the available leave balance.
    // Approves the request and deducts leaves if sufficient.

    @GetMapping
    // GET /api/leaves
    // Retrieves all saved leave requests.
}
```

### What the routes do
- `POST /api/leaves`: checks the employee's leave balance. If enough leaves are available, the request is approved, the balance is deducted, and the request is saved.
- If the balance is insufficient, the API returns a rejection response without deducting the balance or saving the rejected request.
- `GET /api/leaves`: returns all saved leave requests.

Example leave request body:

```json
{
  "employeeId": "YOUR_EMPLOYEE_MONGODB_ID",
  "noOfDays": 2,
  "purpose": "Personal work"
}
```

**Important:** Replace `YOUR_EMPLOYEE_MONGODB_ID` with the employee's actual MongoDB `_id`. Do not use the employee code in this field.

---

## 3. `EmployeeRepository.java`

```java
public interface EmployeeRepository
        extends MongoRepository<Employee, String> {

    // Spring Data MongoDB provides common database operations, including:
    // save()     - inserts or updates an employee
    // findAll()  - retrieves all employees
    // findById() - finds an employee by ID
    // deleteById() - deletes an employee

    Employee findByEmployeeCode(String employeeCode);
    // Finds an employee using their employee code.
}
```

---

## 4. Important annotations

| Annotation | Meaning |
|---|---|
| `@RestController` | Defines a REST controller that handles requests and returns response data. |
| `@RequestMapping` | Defines a common URL path for controller routes. |
| `@PostMapping` | Handles HTTP POST requests, commonly used to submit or create data. |
| `@GetMapping` | Handles HTTP GET requests, commonly used to retrieve data. |
| `@RequestBody` | Converts the incoming JSON request body into a Java object. |
| `@PathVariable` | Reads a value from a URL path segment. |
| `@CrossOrigin` | Allows cross-origin requests from the configured frontend origin. |
| `@Id` | Marks the document identifier field. |
| `@Document` | Maps a Java model class to a MongoDB collection. |

---

## 5. Request flow

1. The Vue frontend sends a request using JavaScript `fetch()`.
2. Spring Boot routes the request to `EmployeeController` or `LeaveController`.
3. The controller uses a repository to read or write data in MongoDB.
4. Spring Boot returns a response to the frontend, which updates the page.

```text
Vue Frontend (port 5173)
        |
        | HTTP request using fetch()
        v
Spring Boot REST API (port 8080)
        |
        +--> EmployeeController --> EmployeeRepository --> employees
        |
        +--> LeaveController ----> repositories / leave logic --> leave_requests
        |
        v
HTTP response (usually JSON)
```

## Practical exam reminder

Remember these four main routes:

- `POST /api/employees` — create an employee
- `GET /api/employees` — list employees
- `POST /api/leaves` — apply for leave
- `GET /api/leaves` — list saved leave requests

The snippets above are explanatory examples. Keep the actual method signatures and business logic from your working project when adding comments to the real source files.
