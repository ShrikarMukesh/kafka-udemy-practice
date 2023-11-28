package com.order.command.action;

import java.time.LocalDateTime;

import com.order.repository.OrderItemRepository;
import com.order.repository.OrderRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.order.api.request.OrderItemRequest;
import com.order.api.request.OrderRequest;
import com.order.broker.message.OrderMessage;
import com.order.broker.producer.OrderProducer;
import com.order.entity.Order;
import com.order.entity.OrderItem;

@Component
public class OrderAction {

	@Autowired
	private OrderProducer orderProducer;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public Order convertToOrder(OrderRequest request) {
		var result = new Order();

		result.setCreditCardNumber(request.getCreditCardNumber());
		result.setOrderLocation(request.getOrderLocation());
		result.setOrderDateTime(LocalDateTime.now());
		result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

		var items = request.getItems().stream().map(this::convertToOrderItem).toList();
		items.forEach(item -> item.setOrder(result));
		
		result.setItems(items);

		return result;
	}

	private OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
		var result = new OrderItem();
		
		result.setItemName(itemRequest.getItemName());
		result.setPrice(itemRequest.getPrice());
		result.setQuantity(itemRequest.getQuantity());
		
		return result;
	}
	
	public void saveToDatabase(Order order) {
		orderRepository.save(order);
		order.getItems().forEach(orderItemRepository::save);
	}

	public void publishToKafka(OrderItem orderItem) {
		var orderMessage = new OrderMessage();
		
		orderMessage.setItemName(orderItem.getItemName());
		orderMessage.setPrice(orderItem.getPrice());
		orderMessage.setQuantity(orderItem.getQuantity());
		
		var order = orderItem.getOrder();
		orderMessage.setOrderDateTime(order.getOrderDateTime());
		orderMessage.setOrderLocation(order.getOrderLocation());
		orderMessage.setOrderNumber(order.getOrderNumber());
		orderMessage.setCreditCardNumber(order.getCreditCardNumber());
		
		orderProducer.publish(orderMessage);
	}
	
}
