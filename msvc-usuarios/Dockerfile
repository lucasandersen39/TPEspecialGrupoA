FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-usuarios/pom.xml ./msvc-usuarios/
COPY msvc-usuarios/src ./msvc-usuarios/src/
RUN mvn -f msvc-usuarios/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8002
COPY --from=builder /app/msvc-usuarios/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]