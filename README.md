# Test task for Clear Solutions

## Description

Task is to app which creates user entities from json, and we can do crud operations.

## Used Technologies

* Spring Boot
* Docker
* Lombok
* MapStruct

## How to launch project with Docker

First you need to clone the repository:<br>
`git clone https://github.com/timmawv/UserService` <br>
Just put this command and app will start<br>
`docker compose up -d`<br>
Run Dockerfile<br>
`docker run --rm --name user_service -p 8080:8080 user_service`

## How to launch project without Docker

First you need to clone the repository:<br>
`git clone https://github.com/timmawv/UserService` <br>
Then build project to jar<br>
`mvn clean package -Dmaven.test.skip`<br>
After packaging project you can launch it <br>
`java -jar target/UserService-0.0.1-SNAPSHOT.jar`

## Postman collection for testing
[Open postman collection](postman_test_collection/users.postman_collection.json)