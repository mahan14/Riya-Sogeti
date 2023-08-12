package com.sogeti.filmland.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sogeti.filmland.model.CategoryResponse;
import com.sogeti.filmland.model.DTO.CategoryDTO;
import com.sogeti.filmland.model.DTO.SubscribedCategoryDTO;
import com.sogeti.filmland.model.entity.Category;
import com.sogeti.filmland.model.entity.Customer;
import com.sogeti.filmland.model.entity.Subscription;
import com.sogeti.filmland.repository.CategoryRepository;
import com.sogeti.filmland.repository.CustomerRepository;
import com.sogeti.filmland.repository.SubscriptionRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setName(category.getName());
        dto.setAvailableContent(category.getAvailableContent());
        dto.setPrice(category.getPrice());
        return dto;
    }
    public CategoryResponse getAvailableAndSubscribedCategories(String customerName) {
    	Customer customer = customerRepository.findByName(customerName);
        List<Category> availableCategories = categoryRepository.findAvailableCategoriesForCustomer(customer.getId());
        List<Subscription> subscribedCategories = subscriptionRepository.findSubscribedCategoriesForCustomer(customer.getId());

        List<CategoryDTO> result = new ArrayList<>();
        List<SubscribedCategoryDTO> resultCategoryDTOs = new ArrayList<>();
        CategoryResponse categoryResponse=new CategoryResponse();

        for (Category category : availableCategories) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            result.add(categoryDTO);
        }
        categoryResponse.setAvailableCategories(result);

        for (Subscription subscription : subscribedCategories) {
           // Category category = subscription.getCategory();
        	SubscribedCategoryDTO subscribedCategoryDTO=new SubscribedCategoryDTO();
        	subscribedCategoryDTO.setName(subscription.getName());
        	subscribedCategoryDTO.setPrice(subscription.getPrice());
        	subscribedCategoryDTO.setRemainingContent(subscription.getRemainingContent());
        	subscribedCategoryDTO.setStartDate(subscription.getStartDate());
//            CategoryDTO categoryDTO = new CategoryDTO();
//            categoryDTO.setId(subscription.getId());
//            categoryDTO.setName(subscription.getName() + " (Subscribed)");
        	resultCategoryDTOs.add(subscribedCategoryDTO);
        }
        categoryResponse.setSubscribedCategories(resultCategoryDTOs);

        return categoryResponse;
    }

}