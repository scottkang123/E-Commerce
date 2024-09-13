package com.ecommerce.coreservices.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"  //get variable from config service config file
)
public interface CustomerClient {
    @GetMapping("/{customer-id}") //in customer service we have api for finding a customer
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
