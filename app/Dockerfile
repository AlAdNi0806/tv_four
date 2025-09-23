FROM openjdk:17-jdk-slim

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build application
RUN mvn clean package -DskipTests

# Run application
CMD ["java", "-jar", "target/monitoring-app-1.0.0.jar"]

EXPOSE 8090