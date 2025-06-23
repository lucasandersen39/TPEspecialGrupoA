FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY msvc-monopatines/pom.xml ./msvc-monopatines/
COPY msvc-monopatines/src ./msvc-monopatines/src/
RUN mvn -f msvc-monopatines/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8001
COPY --from=builder /app/msvc-monopatines/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]