FROM adoptopenjdk/openjdk11:slim

ENV PORT 8080
EXPOSE 8080

COPY target/instructions-api.jar /opt/application.jar

WORKDIR /opt

CMD ["sh", "-c", "java -jar application.jar"]