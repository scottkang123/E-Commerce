package com.ecommerce.coreservices.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    //extends mongo repository interface. String -> id
}
