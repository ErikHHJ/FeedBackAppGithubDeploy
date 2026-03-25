# Use Maven + JDK to build
FROM maven:3.9.4-eclipse-temurin-22-jdk AS build

WORKDIR /app

# Copy pom.xml first for caching dependencies
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:22-jdk

WORKDIR /app

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]