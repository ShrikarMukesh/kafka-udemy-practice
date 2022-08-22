package com.course.kafka.scheduler;

import java.util.List;

import com.course.kafka.model.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.course.kafka.producer.CommodityProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

//@Service
public class CommodityScheduler {

	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private CommodityProducer producer;

	@Scheduled(fixedRate = 5000)
	public void fetchCommodities() {
		var commodities = restTemplate.exchange("http://localhost:9090/api/commodity/v1/all", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Commodity>>() {
				}).getBody();

		//assert commodities != null;
		commodities.forEach(t -> {
			try {
				producer.sendMessage(t);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}

}
