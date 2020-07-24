# Pull from a base image
FROM openjdk:11

# Add the files from the current directory to app/
ADD . target/coronavirus-tracker-0.0.1-SNAPSHOT.jar coronavirus-tracker-0.0.1-SNAPSHOT.jar

# Listen on the specified port
EXPOSE 2991

# Set Entry Point
ENTRYPOINT ["java", "-jar", "/coronavirus-tracker-0.0.1-SNAPSHOT.jar"]

