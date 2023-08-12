package com.sogeti.filmland.model.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribedCategoryDTO {
    private String name;
    private int remainingContent;
    private double price;
    private Date startDate;
    // Getters and setters
}