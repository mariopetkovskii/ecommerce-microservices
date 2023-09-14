FROM maven:3.8-openjdk-17-slim as builder

COPY common-service/src /home/common-service/src
COPY common-service/pom.xml /home/common-service
RUN mvn -f /home/common-service/pom.xml clean install -Dmaven.test.skip=true

COPY user-service/pom.xml /home/user-service/
COPY user-service/src /home/user-service/src
RUN mvn -f /home/user-service/pom.xml clean package -Dmaven.test.skip=true


FROM openjdk:17-jdk-slim

COPY --from=builder /home/user-service/target/*.jar user-service.jar

ENTRYPOINT ["java", "-jar", "/user-service.jar"]

EXPOSE 8082