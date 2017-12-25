FROM openjdk:8-jre-alpine
EXPOSE 8085
ENV CONFIG_SERVER_URL consul
ENV DISCOVERY_HOSTNAME reporting
ADD target/reporting-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]