FROM openjdk:11
EXPOSE 2991
ADD target/coronavirus-tracker-0.0.1-SNAPSHOT.jar coronavirus-tracker-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/coronavirus-tracker-0.0.1-SNAPSHOT.jar"]