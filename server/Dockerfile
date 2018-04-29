FROM openjdk:8-jdk-alpine
ADD target/todo.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar","/app.jar"]