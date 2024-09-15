package com.ecommerce.coreservices.orderline;

public record OrderLineRequest(
        Integer id,
        Integer productId,
        double quantity
) {
}
