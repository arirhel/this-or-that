# This or that

Choose one winner from several options

## Run tests

`mvnw clean install` for Java

`npm install` and `npm test` for JS

## Run sonar

[Sonar Getting Started Docs](https://docs.sonarqube.org/latest/setup/get-started-2-minutes/)

```shell
$ docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
$ .\mvnw sonar:sonar -D sonar.host.url="http://localhost:9000" -D sonar.login=${GENERATED_TOKEN}
```

Log in to http://localhost:9000 with `admin:admin`
