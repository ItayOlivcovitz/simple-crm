# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information about who maintains the image
MAINTAINER "itay"

# Add the application's jar to the container
COPY /target/users-0.0.1-SNAPSHOT.jar users-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","users-0.0.1-SNAPSHOT.jar"]