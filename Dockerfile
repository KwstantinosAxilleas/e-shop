# Use a base image with JDK 17 (or your required version)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build context to the container
COPY target/myspringbootproject-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (optional, but not required by Render)
EXPOSE 8080

# Use the dynamic PORT environment variable provided by Render
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]