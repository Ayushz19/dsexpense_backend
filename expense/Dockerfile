# Build Stage
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/expense-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV PORT=8080
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]
