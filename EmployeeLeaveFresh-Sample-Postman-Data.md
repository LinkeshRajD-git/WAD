# Employee Leave Management System — Sample Postman Data

Use these examples with the Employee Leave Management project.

## Base URL

`http://localhost:8080`

Make sure MongoDB and the Spring Boot backend are running before sending requests.

## 1. Create Employee 1

- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Postman: **Body → raw → JSON**

```json
{
  "name": "Arun Kumar",
  "employeeCode": "EMP101",
  "department": "CSE",
  "email": "arun@example.com",
  "totalLeaves": 12
}
```

Expected: `201 Created`. Copy the generated `id` from the response for leave requests.

## 2. Create Employee 2

- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Body → raw → JSON

```json
{
  "name": "Priya Sharma",
  "employeeCode": "EMP102",
  "department": "IT",
  "email": "priya@example.com",
  "totalLeaves": 15
}
```

## 3. Create Employee 3

- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Body → raw → JSON

```json
{
  "name": "Rahul Raj",
  "employeeCode": "EMP103",
  "department": "ECE",
  "email": "rahul@example.com",
  "totalLeaves": 10
}
```

## 4. Create Employee 4

- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Body → raw → JSON

```json
{
  "name": "Divya S",
  "employeeCode": "EMP104",
  "department": "Mechanical",
  "email": "divya@example.com",
  "totalLeaves": 8
}
```

Employee codes must be unique. If a code already exists, use another one such as `EMP105`.

## 5. List all employees

- Method: `GET`
- URL: `http://localhost:8080/api/employees`

Copy an employee's generated MongoDB `id` from the response. Do not use `employeeCode` as `employeeId`.

## 6. Apply for 2 days of leave

- Method: `POST`
- URL: `http://localhost:8080/api/leaves`
- Body → raw → JSON

Replace `PASTE_REAL_MONGODB_ID_HERE` with the actual `id` returned when creating or listing employees.

```json
{
  "employeeId": "PASTE_REAL_MONGODB_ID_HERE",
  "noOfDays": 2,
  "purpose": "Personal work"
}
```

Expected: `APPROVED` if the employee has at least 2 available leave days. The balance should reduce by 2.

## 7. Test insufficient leave balance

Use an employee ID with 10 or fewer available days and request 20 days:

```json
{
  "employeeId": "PASTE_REAL_MONGODB_ID_HERE",
  "noOfDays": 20,
  "purpose": "Long vacation"
}
```

Expected: `REJECTED` with no leave-balance deduction. Replace the placeholder with a real MongoDB ID.

## 8. List all leave records

- Method: `GET`
- URL: `http://localhost:8080/api/leaves`

## 9. Check MongoDB records

Connect MongoDB Compass to `mongodb://localhost:27017`, select database `employee_leave_db`, then inspect `employees` and `leave_requests`.

Or use `mongosh`:

```javascript
use employee_leave_db
db.employees.find().pretty()
db.leave_requests.find().pretty()
```

## Quick test order

1. Start MongoDB.
2. Start Spring Boot.
3. Create an employee with `POST /api/employees`.
4. Copy its generated `id`.
5. Apply for leave with `POST /api/leaves`.
6. Verify with `GET /api/employees` and `GET /api/leaves`.
7. Confirm records and updated balance in MongoDB Compass.

## Important

- Employee creation requires `name`, `employeeCode`, `department`, `email`, and `totalLeaves`.
- Leave application requires `employeeId`, `noOfDays`, and `purpose`.
- `employeeId` must be the MongoDB-generated employee `id`.
- Use a unique `employeeCode` for each new employee.
