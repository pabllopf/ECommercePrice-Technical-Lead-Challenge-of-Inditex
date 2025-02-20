# Build stage
FROM ubuntu:latest AS build

# Update repositories and prepare the build environment
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y

# Copy the source code into the container
COPY . .

# Ensure gradlew has execution permissions
RUN chmod +x gradlew

# Build the JAR of the project (without daemon to avoid issues in Docker)
RUN ./gradlew bootJar --no-daemon

# Final stage for the production container
FROM openjdk:21-jdk-slim

# Expose port 8080
EXPOSE 8080

# Copy the JAR file that matches the pattern demo-xxx*.jar from the build stage
COPY --from=build /build/libs/demo-*.jar app.jar

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
