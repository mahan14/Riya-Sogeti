package com.sogeti.filmland.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDTO {
    private String error;
    private Object details;

    public ErrorResponseDTO(String error, Object details) {
        this.error = error;
        this.details = details;
    }

    // Getters and setters
}
