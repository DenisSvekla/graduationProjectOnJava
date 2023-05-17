FROM openjdk:11
RUN mkdir /app
WORKDIR /app
COPY target/graduationProjectOnJava-1.0-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/graduationProjectOnJava-1.0-SNAPSHOT.jar