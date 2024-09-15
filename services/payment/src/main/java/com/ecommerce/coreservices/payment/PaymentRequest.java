package com.ecommerce.coreservices.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer //when using complex object mark the class with @Validated
) {
}
