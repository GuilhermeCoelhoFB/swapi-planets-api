## Swapi Planets with Docker
Welcome to Swapi-Planets API! In this Application you can Add, Remove and Search by ID/Name (locally added planets or registered planets on [swapi.dev](https://swapi.dev/api/planets/)).
When adding a planet, if it's a Swapi registered Planet, the number of films appearances will be auto-filled up! 
### How to run this application using Docker:

- <strong>Make sure to have Docker already installed on your computer</strong>, find how to install here: [Docker Hub](https://hub.docker.com)
  
- Open a terminal at the project's root, and run `gradlew build -x test` to build the application. 
  
- All you need to do after the build is run `docker-compose up --build` to create the necessary containers.

You can try the API with Insomnia, there's an `insomnia.json` on the root folder, you can import it in the [Insomnia Desktop](https://insomnia.rest/download) or just click:

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=Swapi%20Planets&uri=https%3A%2F%2Fgithub.com%2FGuilhermeCoelhoFB%2Fswapi-planets-api%2Fblob%2Fmaster%2Finsomnia.json)

###### This is my very first API Rest with SpringBoot/MongoDB/Docker.

Hope you like it!
