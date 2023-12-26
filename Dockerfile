# Use Maven to build the application
FROM maven:3.6.3-jdk-11 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -f pom.xml clean package

# Package the application in a Java runtime container
FROM openjdk:11-jre-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

