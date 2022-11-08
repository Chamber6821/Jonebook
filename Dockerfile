FROM openjdk:17
ADD build/libs/app.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]