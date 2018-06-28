FROM openjdk:8-jdk-alpine
ADD target/spring-boot-mysql.jar spring-boot-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-mysql.jar"]
