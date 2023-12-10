# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/application.jar

# Set the working directory
WORKDIR /app

# Expose the port your application runs on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "application.jar"]
