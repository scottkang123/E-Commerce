package com.ecommerce.coreservices.order;

import java.math.BigDecimal;

public record OrderResponse(
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
    //can fetch from the frontend no need to fetch eagerly
    //can fetch from the backend if really wanted
) {
}
