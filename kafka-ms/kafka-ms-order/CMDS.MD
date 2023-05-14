docker exec -it kafka bash
clearing the kafka bash cmds:
  1) printf "\033c"
  2) alias cls='printf "\033c"'

kafka-topics.sh --bootstrap-server localhost:9092 --list

kafka-topics.sh --bootstrap-server localhost:9092 --topic budjet2023 --create

kafka-producer

kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic t-commodity-order

