# Build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
RUN ls -l /app/target

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ascendionLibrary-0.0.1-SNAPSHOT.jar ascendionLibrary.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ascendionLibrary.jar"]