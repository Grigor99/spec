
FROM openjdk:11-jdk-slim
VOLUME /main-app
ADD target/devops-integration.jar devops-integration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/devops-integration.jar"]

