FROM maven:3.8.2-openjdk-16 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -Dmaven.test.skip

FROM openjdk:16
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/springchallenge-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java","-jar","springchallenge-0.0.1-SNAPSHOT.jar"]



