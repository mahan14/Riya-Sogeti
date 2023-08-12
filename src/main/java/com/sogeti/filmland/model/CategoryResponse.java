package com.sogeti.filmland.model;

import java.util.List;

import com.sogeti.filmland.model.DTO.CategoryDTO;
import com.sogeti.filmland.model.DTO.SubscribedCategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private List<CategoryDTO> availableCategories;
    private List<SubscribedCategoryDTO> subscribedCategories;

}
