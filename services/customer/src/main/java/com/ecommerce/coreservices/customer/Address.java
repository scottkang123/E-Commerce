package com.ecommerce.coreservices.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated //this object is included in validation
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}

