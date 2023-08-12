package com.sogeti.filmland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.model.RegistrationRequest;
import com.sogeti.filmland.model.RegistrationResponse;
import com.sogeti.filmland.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sogeti/filmland/register")
public class CustomerRegisterController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
		RegistrationResponse createdUser = customerService.registerCustomer(registrationRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
}
