FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY target/room-allocation-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]