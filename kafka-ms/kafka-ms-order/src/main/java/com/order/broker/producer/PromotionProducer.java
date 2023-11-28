package com.order.broker.producer;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.order.broker.message.PromotionMessage;

@Service
public class PromotionProducer {

	private static final Logger LOG = LoggerFactory.getLogger(PromotionProducer.class);


	private final KafkaTemplate<String, PromotionMessage> kafkaTemplate;

	public PromotionProducer(KafkaTemplate<String, PromotionMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publish(PromotionMessage message) {
		try {
			var sendResult = kafkaTemplate.send("t-commodity-promotion", message).get();
			LOG.info("Send result success for message {}", sendResult.getProducerRecord().value());
		} catch (InterruptedException | ExecutionException e) {

			LOG.error( "Error publishing {}, because {}", message, e.getMessage());
		}
	}

}
