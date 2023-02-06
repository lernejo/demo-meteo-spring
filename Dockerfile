# (1)
FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
# (2)
RUN mvn -f /home/app/pom.xml clean package

# (3)
FROM eclipse-temurin:17-jdk-focal
# (4)
COPY --from=build /home/app/target/maven_training-1.0.0-SNAPSHOT.jar /usr/local/lib/app.jar
# (5)
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
