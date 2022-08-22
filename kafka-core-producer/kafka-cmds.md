kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t-location --partitions 3 --replication-factor 1
kafka-console-consumer.sh --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-commodity
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group cg-dashboard --describe
-----------------------------------------------------------------------------------------------------------------------------------
kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-rebalance
kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic t-rebalance --partitions 2
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-rebalance
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group group-rebalancer --describe
