package com.order.command.action;

import org.springframework.stereotype.Component;

import com.order.api.request.PromotionRequest;
import com.order.broker.message.PromotionMessage;
import com.order.broker.producer.PromotionProducer;

@Component
public class PromotionAction {

	private final PromotionProducer producer;

	public PromotionAction(PromotionProducer producer) {
		this.producer = producer;
	}

	public void publishToKafka(PromotionRequest request) {
		var message = new PromotionMessage(request.getPromotionCode());
		
		producer.publish(message);
	}

}
