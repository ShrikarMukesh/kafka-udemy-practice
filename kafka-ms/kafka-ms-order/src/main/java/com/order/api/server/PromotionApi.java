package com.order.api.server;

import com.order.api.request.PromotionRequest;
import com.order.command.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApi {


	private final PromotionService service;

	public PromotionApi(PromotionService service) {
		this.service = service;
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody PromotionRequest request) {
		service.createPromotion(request);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(request.getPromotionCode());
	}

}
