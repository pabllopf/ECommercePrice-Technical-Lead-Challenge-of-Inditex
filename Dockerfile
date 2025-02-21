# Build stage
FROM ubuntu:latest AS build

# Update repositories and install OpenJDK 21
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

# Copy the project source code into the container
COPY . .

# Grant execution permissions to Gradle wrapper
RUN chmod +x gradlew

# Build the project JAR file (disabling the Gradle daemon to avoid issues in Docker)
RUN ./gradlew bootJar --no-daemon

# Final stage for the production container
FROM openjdk:21-jdk-slim

# Expose port 8080 to allow external access
EXPOSE 8080

# Copy the built JAR file from the build stage to the final container
COPY --from=build /build/libs/EcommercePriceAPI-*.jar app.jar

# Set the entry point to execute the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
