FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
ADD target/userservice.jar userservice.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/userservice.jar"]