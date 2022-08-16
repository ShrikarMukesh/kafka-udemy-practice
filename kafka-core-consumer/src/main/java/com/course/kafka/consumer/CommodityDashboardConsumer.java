package com.course.kafka.consumer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.course.kafka.model.Commodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class CommodityDashboardConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(CommodityDashboardConsumer.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
	public void consume(String message) throws JsonMappingException, JsonProcessingException, InterruptedException {
		var commodity = objectMapper.readValue(message, Commodity.class);
		
		var randomDelayMillis = ThreadLocalRandom.current().nextLong(500, 2000);
		TimeUnit.MILLISECONDS.sleep(randomDelayMillis);
		
		LOG.info("Dashboard logic for : {}", commodity);
	}
	
}
