FROM openjdk:8-jdk-alpin
COPY target/s3mock-client*.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Ds3.endpoint=${S3_MOCK_URL}","-jar","/app.jar"]