FROM openjdk:15.0.1-slim-buster
COPY target/aarria.war /usr/local/tomcat/webapps/aarria.war
RUN sh -c 'touch /usr/local/tomcat/webapps/aarria.war'
ENTRYPOINT [ "sh", "-c", "java -jar /usr/local/tomcat/webapps/aarria.war" ]