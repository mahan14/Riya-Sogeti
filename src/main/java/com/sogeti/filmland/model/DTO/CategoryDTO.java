package com.sogeti.filmland.model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	private Long id;
 
    private String name;
    private int availableContent;
    private double price;
    
}