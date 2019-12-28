# Spring Boot Kotlin Dynamodb JPA JWT Example Project

##Example of Spring Boot Kotlin DynamoDB + MySQL + JWT 
Run local dynamodb with docker

```docker
docker run -p 8000:8000 amazon/dynamodb-local -jar DynamoDBLocal.jar -inMemory -sharedDb
```

Install DynamoDB Admin with
```shell script
npm install dynamodb-admin -g
```
Export local DynamoDB URL
```shell script
export DYNAMO_ENDPOINT=http://localhost:8000
```

Run in another console
```shell script
dynamodb-admin
```
Open URL http://localhost:8001 and you will see dynamodb admin panel. Then create your DynamoDB table.


Start Springboot application with gradle
```shell script
./gradlew bootRun
```

Open Swagger for API list
 ```html
http://localhost:8080/swagger-ui.html
```
And ...
have a good coding time, my friends ...