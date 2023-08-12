package com.sogeti.filmland.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDTO {
	//@NotBlank(message = "Email is required")
	//@Email(message = "Invalid email format")
	private String email;

	//@NotBlank(message = "Customer is required")
	//@Email(message = "Invalid customer email format")
	private String customerEmail;

	//@NotBlank(message = "Subscribed category is required")
	private String subscribedCategory;

	// Getters and setters
}