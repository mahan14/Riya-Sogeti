package com.sogeti.filmland.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.model.CategoryResponse;
import com.sogeti.filmland.model.DTO.CategoryDTO;
import com.sogeti.filmland.service.CategoryService;

@RestController
@RequestMapping("/sogeti/filmland")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/customer/{customerName}")
    public ResponseEntity<CategoryResponse> getCategoriesForCustomer(@PathVariable String customerName) {
        CategoryResponse categories = categoryService.getAvailableAndSubscribedCategories(customerName);
        return ResponseEntity.ok(categories);
    }
}
