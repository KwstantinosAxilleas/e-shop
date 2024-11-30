# Use a base image with JDK 17 (or your required version)
# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/myspringbootproject-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (optional, helpful for documentation)
EXPOSE 8080

# Use the dynamic PORT environment variable provided by Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]