FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
ADD target/gateway.jar gateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/gateway.jar"]