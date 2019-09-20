FROM openjdk:8-jdk-alpine
MAINTAINER Michal Kaczara <mkaczara@o2.pl>

VOLUME /tmp

ARG JAR_FILE
COPY target/${JAR_FILE} employee-service.jar

ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-jar","/employee-service.jar"]