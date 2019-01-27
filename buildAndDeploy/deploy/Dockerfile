FROM openjdk:8-jdk-alpine
ADD bootcamp-just-cinemas-api.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=production","-jar","/app.jar"]
