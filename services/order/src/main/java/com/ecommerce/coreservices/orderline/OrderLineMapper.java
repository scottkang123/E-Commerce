package com.ecommerce.coreservices.orderline;

import com.ecommerce.coreservices.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request, Order order) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(order)
                .productId(request.productId())
                .build();
    }
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
