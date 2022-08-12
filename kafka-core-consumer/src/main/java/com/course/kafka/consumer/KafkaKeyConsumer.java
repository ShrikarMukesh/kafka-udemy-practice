package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
@Slf4j
public class KafkaKeyConsumer {

    @KafkaListener(topics = "t-twitter" , concurrency = "6")
    public void consume(ConsumerRecord<String, String> consumerRecord ) throws InterruptedException {
        log.info("Key: {}, partition: {}, Message: {}", consumerRecord.key() , consumerRecord.partition() , consumerRecord.value());
        TimeUnit.SECONDS.sleep(1);
    }
}
