FROM openjdk:17-alpine
VOLUME /main-app
ADD target/specification-0.0.1-SNAPSHOT.jar specification-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/specification-0.0.1-SNAPSHOT.jar"]