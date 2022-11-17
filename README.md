Docker Compose cmds

1) core
*  docker-compose-core.yml --> docker-compose -f docker-compose-core.yml -p core up -d
*  docker-compose-core.yml --> docker-compose -f docker-compose-core.yml -p down
      --> After Excecution
          Stopping kafka     ... done
          Stopping zookeeper ... done
          Removing kafka     ... done
          Removing zookeeper ... done
          Removing network kafka-net
          
