Docker Compose cmds

1) core
*  docker-compose-core.yml --> docker-compose -f docker-compose-core.yml -p core up -d
*  docker-compose-core.yml --> docker-compose -f docker-compose-core.yml -p down
      --> After Excecution
          Stopping kafka     ... done   <br/>
          Stopping zookeeper ... done   <br/>
          Removing kafka     ... done   <br/>
          Removing zookeeper ... done   <br/>
          Removing network kafka-net    <br/>
 
 # Stop services only
docker-compose stop

# Stop and remove containers, networks..
docker-compose down 

# Down and remove volumes
docker-compose down --volumes 

# Down and remove images
docker-compose down --rmi <all|local> 


Just to answer the other part of the question:

Use docker-compose up to start or restart all the services defined in a docker-compose.yml.

The docker-compose start command is useful only to restart containers that were previously created, but were stopped. It never creates new containers.

The docker-compose run command is for running “one-off” or “adhoc” tasks.
          
docker exec -it kafka bash

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-commodity-order
