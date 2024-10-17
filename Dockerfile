# First stage: build the application
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven build files into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the project (this generates the JAR in /app/target)
RUN mvn clean package

# Second stage: create a smaller image with only the JAR file
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the generated JAR from the builder stage
COPY --from=builder /app/target/ContactUs-0.0.1-SNAPSHOT.jar /app/ContactUs.jar

# Expose port 8080
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/ContactUs.jar"]
