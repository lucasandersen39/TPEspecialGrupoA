FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn install -N
COPY api-gateway/pom.xml ./api-gateway/
COPY api-gateway/src ./api-gateway/src/
RUN mvn -f api-gateway/pom.xml clean package -DskipTests

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=builder /app/api-gateway/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]