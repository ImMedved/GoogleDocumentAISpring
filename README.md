# Document Processing with Spring Boot and Google Document AI

This project demonstrates how to create a Spring Boot application that processes PDF and image files using Google Document AI. The application takes files or a folder of files as input, sends them to Google Document AI for processing, and converts the extracted text into a DOCX file while preserving the layout.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [Testing](#testing)
- [License](#license)

## Features
- Upload PDF or image files for text extraction.
- Upload a folder containing multiple files for batch processing.
- Use Google Document AI for accurate text extraction.
- Convert extracted text to DOCX format while preserving the layout.

## Technologies
- Java 11
- Spring Boot
- Google Document AI API
- Apache POI (for DOCX conversion)
- JUnit and Mockito (for testing)

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- Google Cloud account with Document AI enabled
- Google Cloud SDK (for authentication and setup)

### Configuration
1. Set up Google Cloud authentication:
   - Create a service account in the Google Cloud Console.
   - Download the JSON key file for the service account.
   - Set the `GOOGLE_APPLICATION_CREDENTIALS` environment variable to the path of the JSON key file:
     ```bash
     export GOOGLE_APPLICATION_CREDENTIALS="path/to/your-service-account-file.json"
     ```

2. Configure the application:
   - Open `src/main/resources/application.properties` and add your Google Document AI processor details:
     ```properties
     google.cloud.project-id=your-project-id
     google.cloud.location=your-processor-location
     google.cloud.processor-id=your-processor-id
     ```

### Running the Application
Start the Spring Boot application:
```bash
mvn spring-boot:run
```

## Testing
Run unit tests with Maven:
```bash
mvn test
```

## License
This project is licensed under the MIT License.
