package com.ecommerce.coreservices.kafka;

import com.ecommerce.coreservices.customer.CustomerResponse;
import com.ecommerce.coreservices.order.PaymentMethod;
import com.ecommerce.coreservices.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
