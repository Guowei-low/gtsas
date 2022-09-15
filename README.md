# gtsas
Gt Student Administrative Systems
A system for admin to manage teacher and student.

## Maven

### Testing
First check that you are able to compile and pass the tests:
```
mvn clean test
```

### Start

To run the backend API locally:

```
mvn spring-boot:run
```

Otherwise, to build it as a fat jar and execute it:

```
mvn clean install 
java -jar target/sas-0.0.1-SNAPSHOT.jar
```

# Server check

To access to site:

```
http://localhost:8080/home 
```
