FROM openjdk:8u181-jdk-slim-stretch
COPY target/s3mock-client*.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Ds3.endpoint=${S3_MOCK_URL}","-jar","/app.jar"]