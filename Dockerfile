# Use the official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build files into the container
COPY . /app

# Run Maven to build the project (this generates the JAR in /app/target)
RUN ./mvnw clean package

# Copy the generated JAR to the correct location
COPY target/ContactUs-0.0.1-SNAPSHOT.jar /app/ContactUs.jar

# Expose port 8080
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/ContactUs.jar"]
