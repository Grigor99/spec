FROM openjdk:11-jdk-slim
VOLUME /main-app
ADD target/devops-integration.jar devops-integration.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/devops-integration.jar"]

RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
  && tar xzvf docker-17.04.0-ce.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-17.04.0-ce.tgz
