#
# Build Stage
#
# Create the container image with below command.
# docker build -t openkoreantext-api:latest .
#
FROM maven:3.8.3-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package Stage
#
# Run the container image with below command.
# docker run -p 8080:4567 --rm --name openkoreantext -i openkoreantext-api:latest
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/org.openkoreantext.api-0.0.1-jar-with-dependencies.jar /usr/local/lib/openkoreantext.jar
EXPOSE 4567
ENTRYPOINT ["java","-jar","/usr/local/lib/openkoreantext.jar"]
