# Workout Service

A comprehensive backend service for managing workout routines, exercises, and user fitness data. Built with Spring Boot and MongoDB, this service provides a scalable foundation for fitness applications.

## Overview

**Workout Service** is a RESTful API that enables users to:
- Create and manage user profiles with fitness metrics and goals
- Build a comprehensive exercise library with muscle group targeting
- Design personalized workout plans combining exercises into structured sessions
- Track fitness data and workout progress

The architecture is designed to be extensible, supporting future features such as:
- Workout history and progress tracking
- Social features and community workouts
- Advanced analytics and performance metrics
- Mobile app integration
- Third-party fitness device synchronization

## Tech Stack

- **Language**: Kotlin 2.3.0
- **Framework**: Spring Boot 3.5.0
- **Database**: MongoDB
- **API Documentation**: OpenAPI/Swagger
- **Build Tool**: Gradle
- **JVM**: Java 21

## Prerequisites

- **Java 21** or higher
- **MongoDB** (running locally or remote instance)
- **Git**
- **Gradle** (optional - wrapper included)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/workout-service.git
cd workout-service
```

### Configure MongoDB

Update the MongoDB connection string in `src/main/resources/config/application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/workout
```

For MongoDB Atlas (cloud), use:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://username:password@cluster.mongodb.net/workout
```

### Build the Project

Using the Gradle wrapper:

```bash
./gradlew build
```

### Run the Application

```bash
./gradlew bootRun
```

The service will start on `http://localhost:8080`

## API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger
- **OpenAPI Spec**: http://localhost:8080/api-docs

## Project Structure

```
src/main/kotlin/
├── ws/
│   ├── user/              # User management (profiles, fitness data)
│   ├── exercises/         # Exercise library and definitions
│   ├── plan/              # Workout plan management
│   ├── api/               # REST controllers and DTOs
│   └── mongo/             # MongoDB configuration and utilities
└── Application.kt         # Spring Boot entry point

src/main/resources/
└── config/
    └── application.yml    # Application configuration
```

## Core Concepts

### Users
User profiles store personal information, fitness metrics, goals, and preferences. Each user can create and manage their own exercises and workout plans.

### Exercises
A comprehensive exercise library defining individual movements, targeting specific muscle groups, and noting required equipment. Exercises can be reused across multiple workout plans.

### Workout Plans
Structured workout sessions composed of multiple exercises. Plans define how many sets, reps, and rest periods for each exercise within a session.

## Development

### Running Tests

```bash
./gradlew test
```

### Code Style

This project follows Kotlin official code style:
```bash
./gradlew ktlintFormat  # if ktlint is configured
```

### Common Tasks

```bash
# Clean build
./gradlew clean

# Build without tests
./gradlew build -x test

# Run with debug output
./gradlew bootRun --debug
```

## Database

MongoDB is used as the primary data store with collections for:
- **users**: User profiles and fitness data
- **exercises**: Exercise definitions
- **plans**: Workout plans

Indexes are configured for optimized queries on commonly searched fields.

## Future Enhancements

Planned features and areas for extension:
- Workout history tracking and analytics
- Performance metrics and progress reporting
- User-generated content moderation
- Social features (sharing, community workouts)
- Mobile app backend optimizations
- Integration with fitness wearables
- Advanced search and filtering capabilities

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or suggestions, please open an issue on the repository or contact the maintainers.

---

**Version**: 1.0-SNAPSHOT  
**Last Updated**: March 2026

