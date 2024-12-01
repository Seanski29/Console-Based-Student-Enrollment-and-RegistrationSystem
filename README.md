# Console-Based-Student-Enrollment-and-RegistrationSystem

## Overview
HELLO! We are the BSIT 2102 | Group 4 from BSU TNEU Lipa Campus here to showcase our semestral final project for Object Oriented Programming CS211

The Console-Based-Student-Enrollment-and-RegistrationSystem is a Java-based console application designed for managing student enrollments in sections, subjects, and professors. 
It allows admins to manage sections and students, while students can register for sections, switch sections, and view their details. Additionally, students can view available subjects and professors.

This system interfaces with a MariaDB database to handle real-time data, such as student authentication, section assignments, and subject registration.

## Features

- **Admin Features**:
  - Admin login.
  - Manage sections and student information.
  
- **Student Features**:
  - Student login.
  - Register for sections.
  - View student details (SR code, section, regular status).
  - Switch sections.
  - View available subjects.
  - View list of professors.

- **Database Integration**:
  - Students and sections are stored in a MariaDB database.
  - Professors and subjects are managed through relational tables.
  - Authentication and registration data are retrieved and updated dynamically.

## Technologies Used

- **Java**: Main programming language used for backend logic.
- **MariaDB**: Relational database for storing and managing data.
- **JDBC**: Java Database Connectivity for interacting with the MariaDB database.

## Prerequisites

To run this project locally, you need the following installed:

- **Java JDK 11+**: Ensure that Java is installed and the `java` command is accessible.
- **MariaDB**: Install MariaDB and configure it on your machine.
- **JDBC Driver**: Ensure the MariaDB JDBC driver is included in your project.

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/Seanski29/Console-Based-Student-Enrollment-and-RegistrationSystem.git
   cd Console-Based-Student-Enrollment-and-RegistrationSystem
