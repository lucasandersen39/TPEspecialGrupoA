FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-autorizacion/pom.xml ./msvc-autorizacion/
COPY msvc-autorizacion/src ./msvc-autorizacion/src/
RUN mvn -f msvc-autorizacion/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8010
COPY --from=builder /app/msvc-autorizacion/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]