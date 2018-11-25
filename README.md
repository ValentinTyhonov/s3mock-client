#S3mock client

This project provides API for easy interaction with mocked S3 ([s3mock](https://hub.docker.com/r/max8github/s3mock/)).

Application can be ran using [docker image](https://hub.docker.com/r/valentintyhonov/s3mock-client/).

###Configurations
As both s3mock and s3mock-client should should be linked, [docker-compose](https://docs.docker.com/compose/) 
can be used for running containers together.

Example of _docker-compose.yml_ file:
```yaml
version: '3'

services:
  server:
    image: max8github/s3mock:0.2.4.1-8-jdk-slim
    ports:
      - "8001:8001"
    volumes:
      - ./server:/var/lib/s3server
    restart: always

  client:
    depends_on:
      - server
    image: valentintyhonov/s3mock-client:latest
    ports:
      - "8008:8008"
    volumes:
      - ./client:/var/lib/s3client
    restart: always
    environment:
      S3_MOCK_URL: http://server:8001
```

In the same directory with _docker-compose.yml_ file should be created _client_ and _server_ directories.
These directories would be mounted as /var/lib/s3client and /var/lib/s3server in created containers.

_client_ directory is needed to there files, user will push to mocked S3 using API.

####Notes:
* to run containers - `docker-compose up -d`
* to update s3mock-client image without stopping s3mock container - `docker-compose up -d --no-deps --build client`
* to stop and remove containers - `docker-compose down`
