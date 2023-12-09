
FROM maven:3.8.5-openjdk-17 AS build

COPY src .
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:17.0.1-jdk-slim

COPY --from=build /app/target/*.jar /app/application.jar



EXPOSE 8080

# Command to run your application
ENTRYPOINT ["java", "-jar", "application.jar"]
COPY --from=build /app/target/*.jar /app/application.jar