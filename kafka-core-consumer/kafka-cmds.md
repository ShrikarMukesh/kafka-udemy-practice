kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t-commodity --partitions 3 --replication-factor 1    <br/>
kafka-console-consumer.sh --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-commodity     <br/>
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group cg-dashboard --describe <br/>
-----------------------------------------------------------------------------------------------------------------------------------
kafka-topics.sh --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-rebalance  <br/>
kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic t-rebalance --partitions 2   <br/>
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic t-rebalance    <br/>
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group group-rebalancer --describe    <br/>
