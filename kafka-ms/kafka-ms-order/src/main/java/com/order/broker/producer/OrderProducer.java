package com.order.broker.producer;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.order.broker.message.OrderMessage;

@Service
public class OrderProducer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

	private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

	public OrderProducer(KafkaTemplate<String, OrderMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void publish(OrderMessage message) {
		var producerRecord = buildProducerRecord(message);

		kafkaTemplate.send(producerRecord)
				.addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {

					@Override
					public void onSuccess(SendResult<String, OrderMessage> result) {
						LOG.info("Order {}, item {} published succesfully", message.getOrderNumber(),
								message.getItemName());
					}

					@Override
					public void onFailure(Throwable ex) {
						LOG.warn("Order {}, item {} failed to publish because {}", message.getOrderNumber(),
								message.getItemName(), ex.getMessage());
					}
				});

		LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
	}

	private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {

		var surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
		var headers = new ArrayList<Header>();
		var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());

		headers.add(surpriseBonusHeader);

		return new ProducerRecord<String, OrderMessage>("t-commodity-order", null, message.getOrderNumber(), message,
				headers);
	}

}
