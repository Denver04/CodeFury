**Bug Tracking System**

**Description**

The Bug Tracking System is a comprehensive application that allows project managers, developers, and testers to manage, report, and resolve bugs. It tracks bugs throughout the software development lifecycle, allowing seamless assignment and monitoring of bug status.

**Features**

1) User roles: Project Manager, Developer, Tester
2) Manage Projects and Bugs
3) Assign and Track Bugs
4) CRUD Operations for Users, Projects, and Bugs
   
**Technologies Used**

1) Frontend: HTML, CSS, Bootstrap, JavaScript
2) Backend: Java (JDBC, DAO Pattern)
3) Database: MySQL
4) Testing: JUnit 5
5) Build Tool: Maven
   
**Prerequisites**

1) HTML, CSS, JS
2) Java 8+
3) Maven
4) MySQL Server
5) IDE: IntelliJ IDEA, Eclipse, or any Java IDE, Vscode for Frontend


**Database Setup**

1) Install MySQL Server.
2) Create a database:
sql
Copy code
CREATE DATABASE bug_tracking_system;
Run the SQL scripts from the /sql directory to create the necessary tables and insert initial data:
sql
Copy code
USE bug_tracking_system;

-- Users Table
CREATE TABLE users (
    user_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    role ENUM('PROJECT_MANAGER', 'DEVELOPER', 'TESTER'),
    created_on DATETIME
);

-- Projects Table
CREATE TABLE projects (
    project_id VARCHAR(50) PRIMARY KEY,
    project_name VARCHAR(100),
    start_date DATE,
    status ENUM('IN_PROGRESS', 'COMPLETED')
);

-- Bugs Table
CREATE TABLE bugs (
    bug_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(100),
    description TEXT,
    severity ENUM('LOW', 'MEDIUM', 'HIGH'),
    status ENUM('OPEN', 'IN_PROGRESS', 'CLOSED'),
    created_on DATETIME,
    created_by VARCHAR(50),
    assigned_to VARCHAR(50),
    project_id VARCHAR(50),
    FOREIGN KEY (created_by) REFERENCES users(user_id),
    FOREIGN KEY (assigned_to) REFERENCES users(user_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);


**Project Setup**

1) Clone the repository:

bash
Copy code
git clone https://github.com/Denver04/CodeFury.git

cd application/


2) Configure MySQL connection: In the DatabaseConnection.java file, update the following parameters:

java
Copy code

private static final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";

private static final String USER = "your-username";

private static final String PASSWORD = "your-password";


3) Build the project using Maven:

bash

Copy code

mvn clean install


**Running the Application**

Via IDE: Open the project in your preferred IDE, configure the main class, and run the application.

Testing: To run unit tests, execute:

bash

Copy code

mvn test


**Functionality**

Add User: Create a new user in the system with roles (Tester, Developer, or Project Manager).

Add Project: Create and assign a project to users.

Report Bug: Testers can report bugs, and Project Managers can assign them to developers.

Track Bug: Monitor the status of bugs through the development cycle.



**Troubleshooting**
Database connection issues: Ensure MySQL server is running and connection details are correct.

Dependencies: Ensure all dependencies are installed via Maven.


**Contributing**
If you’d like to contribute, please fork the repository and submit a pull request!
