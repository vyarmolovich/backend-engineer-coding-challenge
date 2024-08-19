# backend-engineer-coding-challenge

# Hotel Room Allocation API
## Overview
This project provides a RESTful API for optimizing room occupancy in a hotel. The API helps determine how to allocate available Premium and Economy rooms to potential guests to maximize revenue while ensuring customer satisfaction.

## Features
* Room Allocation: Allocates available rooms to guests based on their willingness to pay.
* Revenue Calculation: Computes the total revenue for both Premium and Economy rooms.
* REST API: Exposes a POST endpoint to handle room allocation requests.

## Requirements
* Java Version: Use Java 17 or later.
* Spring Boot: Web framework for building the API.
* Docker: The project is intended to be run in a Docker container using the `eclipse-temurin:21-jdk-jammy` base image.

## Project Structure
* Controller: Handles incoming HTTP requests.
* Service: Contains business logic for room allocation.
* DTOs: Data Transfer Objects for input and output data.
* Tests: Unit and integration tests to validate functionality.

## Building the Project

1. Clone the repository:

```bash
git clone https://github.com/vyarmolovich/backend-engineer-coding-challenge.git
cd backend-engineer-coding-challenge
```

2. Build the project:

```bash
./mvnw clean install
```

3. Running the application locally:

```bash
./mvnw spring-boot:run
```
The application will start on http://localhost:8080.

## Running the Application with Docker
1. Ensure Docker is installed.

2. Run the application using the provided run.sh script:

```bash
./run.sh
```

The script will build and start the application in a Docker container using the `eclipse-temurin:21-jdk-jammy` image. The application will be accessible on port `8080`.

## Testing the Application
Unit and integration tests are provided to validate the core functionality of the API. To run the tests, execute:

```bash
./mvnw test
```

Tests include:

* Controller Tests: Validate the correctness of the API endpoints.
* Service Tests: Validate the business logic for room allocation.

## Build and Run the Project with Docker Compose
1. Ensure Docker and Docker Compose are installed on your machine.

2. Build and start the application:

In the root directory of your project, run:
```bash
docker-compose up --build
```

3. Access the application:

Once the application starts, it will be accessible at http://localhost:8080.

4. Stopping the application:

To stop the application, press `CTRL+C` in the terminal where the `docker-compose` command was run.

Alternatively, you can stop and remove the container with:
```bash
docker-compose down
```

## Using the API

### Endpoint
* POST ```/occupancy```

### Request Body
The request body should include the number of available Premium and Economy rooms and a list of potential guests:

```json
{
"premiumRooms": 7,
"economyRooms": 5,
"potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

### Response Body
The API will return a response indicating the number of occupied rooms and the total revenue generated:

```json
{
"usagePremium": 6,
"revenuePremium": 1054,
"usageEconomy": 4,
"revenueEconomy": 189.99
}
```

### Example Usage
Use curl or any HTTP client to send a request to the API:

```bash
curl -X POST http://localhost:8080/occupancy \
-H "Content-Type: application/json" \
-d '{
"premiumRooms": 7,
"economyRooms": 5,
"potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}'
```

## Additional Notes

* Port Configuration:
  The application is set to run on port `8080` by default. If you need to change the port, modify the `application.properties` file or pass the server.port parameter when running the application.

* Docker: 
  The `run.sh` script ensures that the application is built and runs within a Docker container. Ensure that Docker is installed and running on your machine.