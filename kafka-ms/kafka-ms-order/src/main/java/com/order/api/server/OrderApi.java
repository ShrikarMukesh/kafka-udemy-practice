package com.order.api.server;

import com.order.api.request.OrderRequest;
import com.order.api.response.OrderResponse;
import com.order.command.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderApi {


	private final OrderService orderService;

	public OrderApi(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
		// 1. save order using service
		var orderNumber = orderService.saveOrder(request);
		
		// 2. create response
		var orderResponse = new OrderResponse(orderNumber);
		
		// 3. return response
		return ResponseEntity.ok().body(orderResponse);
	}

}
