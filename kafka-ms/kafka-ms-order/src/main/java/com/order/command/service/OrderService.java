package com.order.command.service;

import com.order.command.action.OrderAction;
import org.springframework.stereotype.Service;

import com.order.api.request.OrderRequest;

@Service
public class OrderService {

	private final OrderAction orderAction;

	public OrderService(OrderAction orderAction) {
		this.orderAction = orderAction;
	}

	public String saveOrder(OrderRequest request) {
		var order = orderAction.convertToOrder(request);
		orderAction.saveToDatabase(order);
		
		// flatten message & publish
		order.getItems().forEach(orderAction::publishToKafka);
		
		return order.getOrderNumber();
	}

}
