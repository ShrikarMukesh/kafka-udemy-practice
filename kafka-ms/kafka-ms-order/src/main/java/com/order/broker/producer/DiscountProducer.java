package com.order.broker.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.broker.message.DiscountMessage;

@Service
public class DiscountProducer {

	private final KafkaTemplate<String, DiscountMessage> kafkaTemplate;

	public DiscountProducer(KafkaTemplate<String, DiscountMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publish(DiscountMessage message) {
		kafkaTemplate.send("t-commodity-promotion", message);
	}

}
