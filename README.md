# Instructions API
This API is responsible to bring the output of a desired instruction scenario.

The user can pass an area size representing the area of the scenario, the position that it will begin
in this scenario, the coordinate of oil patches that exist in this scenario and instructions of navigation.

The API answers with the amount of oil patches that were found during 
the navigation in the scenario and with the final position where the last navigation instruction stopped.

## Requirements

* [Java Open JDK 11](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04)
* [Maven](https://maven.apache.org/install.html)
* [Docker](https://docs.docker.com/engine/install/ubuntu/#installation-methods)
* [Docker Compose](https://docs.docker.com/compose/install/#install-compose-on-linux-systems)
* [Git](https://git-scm.com/downloads)

## Setting up the environment


### Starting application locally in Docker
In the root of the project type the following command on terminal:

`docker-compose up`

This command will build and start the image: instructions-api.
It will start this API using Dockerfile from docker hub public repository.

Attention: If you have any problems on using the command docker-compose, try it using "sudo".

### Checking swagger
After the application is already up, you can check the end-points of
this application on:

`http://localhost:8080/swagger-ui.html`

You'll see 1 end-point:

- `POST /instructions`
  (create an instruction based on the request body)

