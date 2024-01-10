# SpringBoot API
## Documentation
- We have create our spring project in [Spring Initializr webSite](https://start.spring.io)
- Then we downloaded it and opened it in intelij 
- Then we have created a package called controler 
- And we have created class called TestController with this content just for test
```
package tech.chilo.sa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "test")
public class TestController {

    @GetMapping(path = "String")
    public String getString() {
        return "Hello World";
    }

    @GetMapping
    public List<String> getList() {
        return List.of("Hello ", "World");
    }
}
```
- Then we have run this Api and tested it using postman
- Then we have created a docker compose file under ressources to use `mariaDB` and `Adminer`to manage the Db
```
version: '3.1'

services:

  db:
    image: mariadb:11.1
    restart: always
    environment:
      MARIADB_ROOT_PASSWORD: root
    ports:
      - 3307:3306

  adminer:
    image: adminer
    restart: always
    ports:
      - 8081:8080
```
- Then we have open the terminal and navigated to `src/main/resources` and run this command:
```
docker-compose up -d
```
To pull all the images and create the services defined in docker-compose.yaml.
- To see if my container are up
```
docker ps -a
```
- Then i have opened [Adminer](http://localhost:8081) in the browser
- Then i have logged in by this servername `resources-db-1` and maria username and password
- Then i created a file under resources called `sa.sql`
```
CREATE DATABASE SA;

CREATE TABLE CLIENT(
    ID integer primary key not null AUTO_INCREMENT,
    EMAIL varchar(50)
);

CREATE TABLE SENTIMENT(
    ID integer primary key not null AUTO_INCREMENT,
    TEXTE varchar(50),
    TYPE varchar(10),
    CLIENT_ID integer,
    CONSTRAINT client_fk FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT(ID)
);
```
- Then we have create a database in Adminer called `SA` and the tables
- Then we navigated to `src/main/resources/application.properties` to create the connection with 
the database and u can file the documentation of spring [spring doc](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.data)
- Then i have added db connection to the `application.properties`
```
spring.datasource.url=jdbc:mariadb://localhost:3307/SA
spring.datasource.username=root
spring.datasource.password=root
ng.datasource.driver-class-name=org.mariadb.jdbc.Driver

server.servlet.context-path=/api
```
- Then we have runned the project to check the state of the app
- Then i have created the entites package that contains all the entities
- Then we have deleted the TestController
- And we have created a new controller [ClientController](src/main/java/tech/chilo/sa/controller/ClientController.java)
- Then i have created a new package called `service`
- Then we have created under it a new class [ClientService](src/main/java/tech/chilo/sa/service/ClientService.java)
- Then i have created a new package called `repository`
- Then i have created under it a new interface [ClientRepository](src/main/java/tech/chilo/sa/repository/ClientRepository.java)
- Then i have started the dependency injection i have injected the `ClientRepository` in `ClientService`and then i have injected `ClientService`in `ClientController`
- Then we restarted the server and it got shut down automatically Then we have verified `pom.xml` file and we were using jdk 17 change it 
```
mvn clean install
```
- Then we have run it from the terminal
```
mvn spring-boot:run
```
- Then we have test the api in postman
- Then i have added the get client and get client by id
- Then i have created a new class under the entites package called [Sentiment](src/main/java/tech/chilo/sa/entites/Sentiment.java)
- And i have created a package called `enums` and created an enum [TypeSentiment](src/main/java/tech/chilo/sa/enums/TypeSentiment.java) 
- Then i have created the controller [SentimentController](src/main/java/tech/chilo/sa/controller/SentimentController.java)
- Then i have created the Service [SentimentService](src/main/java/tech/chilo/sa/service/SentimentService.java)
- Then i have created the repository [SentimentRepository](src/main/java/tech/chilo/sa/repository/SentimentRepository.java)
- Then i have created the POST sentiment `creer` and GET sentiment `rechercher`
- Then i have added DELETE sentiment `supprimer` and PUT sentiment `modifier`

## Populare HTTP Response status code
| Status Code            | Description                                                                                                                                                                                                                                                                                         |
|------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 200 OK                 | Successfull **GET** returning Content                                                                                                                                                                                                                                                               |
| 201 Created            | Resource was partially created                                                                                                                                                                                                                                                                      |
| 204 NO-CONTENT         | Empty response for **DELETE** or **PUT**                                                                                                                                                                                                                                                            |
| 400 Bad Request        | Resource data pre-validation error.<br/>Resource data invalid.<br/>Resource unknown error.<br/>The request data is invalid.<br/>Resource collection paging error.<br/>The paging limit exceeds the allowed number.<br/>Resource collection ordering error.<br/>Resource collection filtering error. |
| 403 Forbidden          | Access denied.                                                                                                                                                                                                                                                                                      |
| 404 Not Found          | Resource not found.                                                                                                                                                                                                                                                                                 |
| 405 Method Not Allowed | Resource does not support method.<br/>Resource method not implemented yet.                                                                                                                                                                                                                          |
| 500 Internal Error     | Unhandled simple errors.<br/>Resource internal error.                                                                                                                                                                                                                                               |

## Spring Boot REST API Architecture
![Architecture imge](readme_images/spring_api_architecture.gif "Spring API Architecture")