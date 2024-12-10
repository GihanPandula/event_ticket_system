# Event Ticket System

## Introduction
The Event Ticket System is a web application designed to facilitate the management and sale of event tickets. It provides functionalities for vendors to register, log in, and manage their events and tickets. The system is built using Spring Boot for the backend and Angular for the frontend.

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Angular CLI 12 or higher
- npm 7 or higher
- Maven 3.8 or higher

### How to Build and Run the Application

#### Backend
1. Navigate to the `Backend` directory:
    ```sh
    cd Backend
    ```
2. Build the project using Maven:
    ```sh
    mvn clean install
    ```
3. Run the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

#### Frontend
1. Navigate to the `Frontend` directory:
    ```sh
    cd Frontend
    ```
2. Install the dependencies using npm:
    ```sh
    npm install
    ```
3. Start the Angular application:
    ```sh
    ng serve
    ```

## Usage Instructions

### How to Configure and Start the System
1. Ensure the backend and frontend applications are running as described in the setup instructions.
2. Access the frontend application by navigating to `http://localhost:4200` in your web browser.

### Explanation of UI Controls
- **Login/Register**: Vendors can log in or register using the forms provided on the home page.
- **Dashboard**: After logging in, vendors are redirected to their dashboard where they can manage their events and tickets.
- **Event Management**: Vendors can create, update, and delete events. Each event can have multiple ticket types.
- **Ticket Management**: Vendors can manage ticket sales, view ticket statistics, and handle customer inquiries.