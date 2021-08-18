# This or that

INACTIVE - This app currently works at a bare minimum but halting development as MVC is tightly coupling the 
data and views, as it does. I think there's a better architecture for an app like this where a list is 
updated in-flight. Part of the data of this app is updated infrequently (what would be the "vetted" question / options sets)
and part is updated very frequently (when the user is choosing between options). I need to explore better ways to 
handle the two types of state. Rn there's too much pulled in together-- I don't think it would be maintainable in the future 
if I continue development on it as it.

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
