FROM openjdk:21

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "app.jar"]