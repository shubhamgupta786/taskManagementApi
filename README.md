
# Task Management API

A RESTful API for managing tasks with user authentication using Spring Boot.

## Project Overview

This API allows users to:
- Register and login to get JWT tokens
- Create, view, update, and delete tasks
- Manage task details including title, description, and status

## Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security with JWT Authentication
- Spring Data JPA
- H2 Database (Development)
- PostgreSQL (Production)
- Maven

## Local Setup Instructions

### Prerequisites

- Java JDK 17+
- Maven
- Git

### Clone the Repository

```bash
git clone https://github.com/shubhamgupta786/task-management-api.git
cd task-management-api
```
### Build and Run

```bash
# Build the project
mvn clean install

# Run the application (default profile is dev with H2 database)
mvn spring-boot:run
```
The API will start on `http://localhost:8080`
## 📬 API Endpoints Overview

### 🔐 Authentication

#### 📌 Register a User
- Select the **"Register User"** request.
- Request Type: **"POST"**
- The request body should be pre-filled with:

```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```
- Click Send.

- You should receive a **"User registered successfully!**" response.

### 🔑 Login
- Select the **"Login"** request.
- Request Type: **"POST"**
- The request body should be pre-filled with:

```json
{
  "username": "testuser",
  "password": "password123"
}
```
- Click Send.
- You should receive a response containing a JWT token.

#### ✅ Task Management
### 📝 Create a Task
- Select the **"Create Task"** request.
- Verify that the Authorization tab is set to Bearer Token using the {{auth_token}} variable.
- Request Type: **"POST"**
- The request body should be pre-filled with:
```json
{
  "title": "Complete API project",
  "description": "Finish the task management API project"
}
```
- Click Send.
- You should receive a response with the created task details.

### 📋 Get All Tasks
- Select the **"Get All Tasks"** request.
- Verify that the Authorization is correctly set.
- Request Type: **"GET"**
- Click Send.
- You should see a list of all tasks, including the one you just created

### 🔍 Get Task by ID
- Select the "Get Task by ID" request.
- Update the URL to include the specific task ID from the previous response, e.g.:/api/tasks/1
- Request Type: **"GET"**
- Click Send.
- You should see the details for the specific task.

### ✏️ Update Task
- Select the "Update Task" request.
- Update the URL with the correct task ID.
- Request Type: **"PUT"**
- The request body should be pre-filled with:

```json
{
  "title": "Updated task title",
  "description": "Updated task description",
  "status": "IN_PROGRESS"
}
```
- Click Send.
- You should receive the updated task details in the response.

### ❌ Delete Task
- Select the "Delete Task" request.
- Update the URL with the correct task ID.
- Request Type: **"DELETE"**
- Click Send.
- You should receive a Task deleted successfully response.

### 🔁 Verify Deletion
- Run the "Get All Tasks" request again.
- The deleted task should no longer appear in the list.

