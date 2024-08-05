FROM openjdk:22-ea-33-jdk-oraclelinux7
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]