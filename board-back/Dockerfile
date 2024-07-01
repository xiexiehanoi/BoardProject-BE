FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY build/libs/board-back-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 6200
ENTRYPOINT ["java", "-jar", "/app.jar"]
