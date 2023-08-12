package com.sogeti.filmland.schedul;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sogeti.filmland.service.CustomerService;

@Component
public class SubscriptionScheduler {

    private final CustomerService customerService;

    public SubscriptionScheduler(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Run at midnight on the 1st day of each month
    public void processSubscriptions() {
        // Implement subscription and payment processing logic here
    }
}
