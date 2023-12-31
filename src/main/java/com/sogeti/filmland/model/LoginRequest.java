package com.sogeti.filmland.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	
	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	/*
	 * @NotBlank private String availableCategory;
	 */

}
