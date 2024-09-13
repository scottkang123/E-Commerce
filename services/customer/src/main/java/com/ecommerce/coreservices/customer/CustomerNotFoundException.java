package com.ecommerce.coreservices.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) //call superclass of this class
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;

}
