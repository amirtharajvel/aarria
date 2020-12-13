FROM openjdk:15.0.1-slim-buster
ADD target/retail-0.0.1-SNAPSHOT.jar retail-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","retail-0.0.1-SNAPSHOT.jar"]