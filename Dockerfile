FROM openjdk:11-jre-slim

WORKDIR /minance

COPY build/libs/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "/minance/app.jar"]
