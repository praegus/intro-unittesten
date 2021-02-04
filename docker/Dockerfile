FROM openjdk:11.0.8-jre-slim
RUN apt update
RUN apt install -y curl
COPY unit-test-training-0.1.0.jar  app.jar
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar
EXPOSE 8080
