FROM openjdk:17-jdk-alpine as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:resolve

COPY src src

RUN ./mvnw -B package

FROM openjdk:17-jdk-alpine as final
COPY --from=build target/sas-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "sas-0.0.1-SNAPSHOT.jar"]