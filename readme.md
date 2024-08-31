Charity Donation Management System
Overview
The Charity Donation Management System is a web-based application designed to streamline the process of managing donations to various charitable institutions. This project is built using Java, Spring Boot, and Hibernate, with a focus on providing a user-friendly interface for administrators to manage users, donations, and institutions.

Features
User Management: Administrators can view, add, edit, and delete users. The application supports role-based access control.
Institution Management: Administrators can manage institutions by adding, editing, and deleting them. Each institution is associated with donations.
Donation Management: The system tracks donations made to different institutions, providing insights into donation counts and quantities.
Role-Based Security: The application employs Spring Security to enforce role-based access control, ensuring that only authorized users can perform certain actions.
Data Validation: The system incorporates data validation to ensure that all inputs meet the necessary criteria before being processed.
Technologies Used
Backend: Java, Spring Boot, Hibernate, Spring Security
Frontend: JSP, JSTL, Bootstrap
Database: MySQL (or any other relational database supported by Hibernate)
Build Tool: Maven
Version Control: Git
Getting Started
Prerequisites
Before you begin, ensure you have the following installed:

Java 11 or later
Maven 3.6.3 or later
MySQL (or any other relational database)
Installation
Clone the repository:

bash
Skopiuj kod
git clone https://github.com/your-username/charity-donation-management.git
cd charity-donation-management
Configure the database:

Update the application.properties file with your database credentials:

properties
Skopiuj kod
spring.datasource.url=jdbc:mysql://localhost:3306/charity_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build the project:

Use Maven to build the project:

bash
Skopiuj kod
mvn clean install
Run the application:

Start the application using Maven:

bash
Skopiuj kod
mvn spring-boot:run
Access the application:

Open your browser and go to http://localhost:8080/index-admin.

Usage
Login: Use the default admin credentials provided in the data.sql file or create a new admin user.
Manage Users: Navigate to the Users section to view, add, edit, or delete users.
Manage Institutions: Navigate to the Institutions section to manage charitable institutions and view associated donations.
View Donations: Track donation activity and view detailed statistics in the Donations section.
Project Structure
src/main/java/pl/coderslab/charity: Contains the main Java classes for the application.
controller: Handles web requests and manages application flow.
model: Contains entity classes that map to database tables.
service: Business logic and service layer.
repository: Data access layer using Spring Data JPA.
exceptions: Custom exception handling classes.
src/main/resources: Contains application configuration files and static resources.
templates: JSP files for the user interface.
application.properties: Configuration for the Spring Boot application.
