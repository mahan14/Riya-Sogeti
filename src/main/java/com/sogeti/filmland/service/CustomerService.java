package com.sogeti.filmland.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.model.LoginRequest;
import com.sogeti.filmland.model.NotFoundException;
import com.sogeti.filmland.model.RegistrationRequest;
import com.sogeti.filmland.model.RegistrationResponse;
import com.sogeti.filmland.model.ResponseDTO;
import com.sogeti.filmland.model.entity.Customer;
import com.sogeti.filmland.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	public RegistrationResponse registerCustomer(RegistrationRequest registrationRequest) {
		Customer customer = modelMapper.map(registrationRequest, Customer.class);
		customer.setName(customer.getEmail().substring(0, customer.getEmail().indexOf("@")));
		return modelMapper.map(customerRepository.save(customer), RegistrationResponse.class);
	}

	public ResponseDTO loginCustomer(LoginRequest loginRequest) {

		Customer customer = modelMapper.map(loginRequest, Customer.class);
		Customer customerCheck = customerRepository.findByEmail(customer.getEmail());
		if (customerCheck == null) {
			return new ResponseDTO("Login Failed", "Customer not found! Please Signup before Login");

		} else if (!((customerCheck.getPassword().toLowerCase()).equals(customerCheck.getPassword().toLowerCase()))) {
			return new ResponseDTO("Login Failed", "Oops ! Wrong Credentials !!");
		}

		else
			return new ResponseDTO("Login Succesfull", "Enjoy your time !");
	}
}
