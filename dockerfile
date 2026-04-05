FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM tomcat:9.0-jdk17-openjdk-slim

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /home/app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD catalina.sh run

