FROM java:8
RUN mkdir -p /usr/local/test
COPY ./demo-0.0.1-SNAPSHOT.jar /usr/local/test/app.jar
ENTRYPOINT ["java","-jar","/usr/local/test/app.jar"]