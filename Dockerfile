# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory into the container
COPY target/ContactUs-0.0.1-SNAPSHOT.jar /app/ContactUs.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/ContactUs.jar"]
