package com.sogeti.filmland.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseDTO {
	private String status;
	private String message;

	public ResponseDTO(String status, String message) {
		this.status = status;
		this.message = message;
	}

}
