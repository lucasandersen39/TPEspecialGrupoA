FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-ejemploautorizacion/pom.xml ./msvc-ejemploautorizacion/
COPY msvc-ejemploautorizacion/src ./msvc-ejemploautorizacion/src/
RUN mvn -f msvc-ejemploautorizacion/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8050
COPY --from=builder /app/msvc-ejemploautorizacion/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]