package com.sogeti.filmland.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.model.BadRequestException;
import com.sogeti.filmland.model.ErrorResponseDTO;
import com.sogeti.filmland.model.NotFoundException;
import com.sogeti.filmland.model.SubscriptionRequest;
import com.sogeti.filmland.model.DTO.SubscriptionRequestDTO;
import com.sogeti.filmland.model.DTO.SubscriptionResponseDTO;
import com.sogeti.filmland.service.SubscriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping("/subscribe")
	public ResponseEntity<String> subscribeToCategory(@RequestBody SubscriptionRequest request) {
		try {
			subscriptionService.subscribeToCategory(request.getEmail(), request.getAvailableCategory());
			return ResponseEntity.ok("Subscribed successfully");
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (BadRequestException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@Autowired
	public SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}

	@PostMapping("/share-category")
	public ResponseEntity<SubscriptionResponseDTO> shareSubscription(
			@Valid @RequestBody SubscriptionRequestDTO request) {
		SubscriptionResponseDTO response = subscriptionService.shareSubscription(request);
		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());

		ErrorResponseDTO errorResponse = new ErrorResponseDTO("Validation Error", errors);
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
		ErrorResponseDTO errorResponse = new ErrorResponseDTO("Internal Server Error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
