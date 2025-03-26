FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/arilines-0.0.1-SNAPSHOT.jar arilines.jar
# Copy flights.csv into the container's resources folder
COPY src/main/resources/flights.csv /app/resources/flights.csv
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "arilines.jar"]
