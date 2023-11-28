package com.order.command.service;

import org.springframework.stereotype.Service;

import com.order.api.request.PromotionRequest;
import com.order.command.action.PromotionAction;

@Service
public class PromotionService {

	private final PromotionAction action;

	public PromotionService(PromotionAction action) {
		this.action = action;
	}

	public void createPromotion(PromotionRequest request) {
		action.publishToKafka(request);
	}
	
}
