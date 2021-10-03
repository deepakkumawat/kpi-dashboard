# kpi-dashboard
KPI Dashboard for visualising the marketing data

## Technologies used
 - Java 8
 - Spring boot
 - Spring batch
 - H2 database
 - AngularJS
 - Angular material
 - Google chart

## Getting started
Windows users, please replace `gradlew` with `gradlew.bat`.

#### Build
Once you clone (or download) this repository, do below
```$xslt
$ cd kpi-dashboard
$ ./gradlew build
```
#### Running service locally
Gradle task `./gradlew build` will generate the service jar under [build/libs/](build/libs/).
Use command below to execute service locally.
```
$ java -jar data-processing-0.0.1-SNAPSHOT.jar &
```

### API Docs
- Application url: http://localhost:8080/
- Application swagger url: http://localhost:8080/swagger-ui/
- H2 database console: http://localhost:8080/h2-console/