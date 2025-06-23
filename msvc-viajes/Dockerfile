FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-viajes/pom.xml ./msvc-viajes/
COPY msvc-viajes/src ./msvc-viajes/src/
RUN mvn -f msvc-viajes/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8007
COPY --from=builder /app/msvc-viajes/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]