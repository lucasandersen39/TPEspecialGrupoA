FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-paradas/pom.xml ./msvc-paradas/
COPY msvc-paradas/src ./msvc-paradas/src/
RUN mvn -f msvc-paradas/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8003
COPY --from=builder /app/msvc-paradas/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]