# Use Maven + JDK to build
FROM maven:3.9.4-eclipse-temurin-22 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first (for dependency caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use JDK runtime for running the app
FROM eclipse-temurin:22-jdk

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]