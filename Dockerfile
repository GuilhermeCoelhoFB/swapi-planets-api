FROM openjdk

WORKDIR /swapi-app

COPY build/libs/swapi-planets-0.0.1.jar /swapi-app/swapi.jar

ENTRYPOINT ["java", "-jar", "swapi.jar"]

