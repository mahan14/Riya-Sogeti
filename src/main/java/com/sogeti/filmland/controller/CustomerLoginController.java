package com.sogeti.filmland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sogeti.filmland.model.LoginRequest;
import com.sogeti.filmland.model.ResponseDTO;
import com.sogeti.filmland.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sogeti/filmland/login")
public class CustomerLoginController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody LoginRequest loginRequest) {
		ResponseDTO responseDTO = customerService.loginCustomer(loginRequest);
		return ResponseEntity.ok(responseDTO);
	}
}
