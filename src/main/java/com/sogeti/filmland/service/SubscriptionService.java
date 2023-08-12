package com.sogeti.filmland.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sogeti.filmland.model.BadRequestException;
import com.sogeti.filmland.model.NotFoundException;
import com.sogeti.filmland.model.DTO.SubscriptionRequestDTO;
import com.sogeti.filmland.model.DTO.SubscriptionResponseDTO;
import com.sogeti.filmland.model.entity.Category;
import com.sogeti.filmland.model.entity.Customer;
import com.sogeti.filmland.model.entity.Subscription;
import com.sogeti.filmland.repository.CategoryRepository;
import com.sogeti.filmland.repository.CustomerRepository;
import com.sogeti.filmland.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public void subscribeToCategory(String email, String categoryName) {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			throw new NotFoundException("Customer not found");
		}

		Category category = categoryRepository.findByName(categoryName);
		if (category == null) {
			throw new NotFoundException("Category not found");
		}

		if (customer.getSubscribedCategories().contains(category)) {
			throw new BadRequestException("Customer is already subscribed to this category");
		}

		customer.getSubscribedCategories().add(category);
		Subscription subscription = new Subscription();
		subscription.setCategory(category);
		subscription.setCustomer(customer);
		subscription.setName(category.getName());
		subscription.setPrice(category.getPrice());
		subscription.setRemainingContent(category.getAvailableContent());
		subscription.setStartDate(new Date());

		// customerRepository.save(customer);
		subscriptionRepository.save(subscription);
	}

	public SubscriptionResponseDTO shareSubscription(SubscriptionRequestDTO request) {
		// Business logic to share subscription and calculate costs
		// This is where you would implement the shared subscription logic

		Customer existingCustomer = customerRepository.findByEmail(request.getEmail());
		if (existingCustomer == null) {
			throw new NotFoundException("Existing customer not found");
		}
		Subscription subscription = subscriptionRepository.findByName(request.getSubscribedCategory());
		Subscription subscribedCategory = subscriptionRepository
				.findSubscribedCategoryForCustomer(existingCustomer.getId(), subscription.getId());

		// .orElseThrow(() -> new NotFoundException("Existing customer not found"));

		Customer sharedCustomer = customerRepository.findByEmail(request.getCustomerEmail());

		if (sharedCustomer == null) {
			throw new NotFoundException("Shared customer not found");
		}

		double sharedCosts = subscription.getPrice() / 2;
		int remainingContent = subscription.getRemainingContent() / 2;

		subscribedCategory.setPrice(sharedCosts);

		subscribedCategory.setRemainingContent(remainingContent);

		subscriptionRepository.save(subscribedCategory);
		System.out.println("saved " + subscribedCategory.toString());

		Subscription newSubscription = new Subscription();
		Category category = categoryRepository.findByName(request.getSubscribedCategory());

		newSubscription.setPrice(sharedCosts);
		newSubscription.setRemainingContent(remainingContent);
		newSubscription.setCustomer(sharedCustomer);
		newSubscription.setStartDate(new Date());
		newSubscription.setCategory(category);

		subscriptionRepository.save(newSubscription);
		System.out.println("saved " + newSubscription.toString());

		SubscriptionResponseDTO response = new SubscriptionResponseDTO();
		response.setStatus("Subscription shared successfully");
		response.setMessage("Subscription for category '" + request.getSubscribedCategory() + "' has been shared.");

		return response;
	}
}
