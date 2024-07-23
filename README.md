# GitHub Repo Listing Application

## Introduction

The "GitHub Repo Listing" application is designed to fetch and display repositories from GitHub based on a user's username. It leverages the GitHub API to retrieve repository data and provides an intuitive REST API for querying and displaying this information. The application uses Spring Boot and integrates with Swagger for API documentation.

## Technologies Used

- **Spring Boot**: Framework for building Java-based applications.
- **Spring Web**: Provides RESTful web services and HTTP functionality.
- **Swagger (Springdoc OpenAPI)**: Enables API documentation and interactive API exploration.
- **Lombok**: Reduces boilerplate code with annotations.
- **Docker**: Used for containerization and deployment.

## API Endpoints

### GitHubController

- **GET /api/github/user/{username}/repositories**: Retrieve a list of repositories for a specified GitHub username.

## How to Run with Docker

To run the application using Docker, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YourUsername/github-repo-listing.git
2. **Navigate to the project root directory:**
   ```bash
   cd github-repo-listing
3. **Build the Maven project:**
   Ensure you have built the JAR file for the application. You can use:
   ```bash
   mvn clean package
4. **Build the Docker image:**
   ```bash
   docker build -t github-repo-listing .
5. **Run the Docker container:**
   ```bash
   docker-compose up
   ``` 
   This will start the application on port 8080.

## Docker Setup

### Dockerfile

The Dockerfile used for building the Docker image:

```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/github-repo-listing-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```
### Docker Compose
You can also use Docker Compose to manage your container setup. Below is the docker-compose.yml configuration:
```docker-compose.yml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: default
```


## Prerequisites

Before running the application, ensure you have the following installed:

- [Docker](https://www.docker.com/get-started): Docker is used for containerization.
- [Docker Compose](https://docs.docker.com/compose/install/): Docker Compose is used to manage multi-container applications.


## Swagger API Documentation

Swagger provides interactive documentation for the API. After starting the application, you can access Swagger UI at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Swagger UI allows you to explore and test the API endpoints directly from your browser.