package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class KafkaHelloConsumer {

    @KafkaListener(topics = "t-hello")
    public void consume(String message){
        System.out.println(message);
    }
}
