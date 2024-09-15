package com.ecommerce.coreservices.order;

import com.ecommerce.coreservices.customer.CustomerClient;
import com.ecommerce.coreservices.exception.BusinessException;
import com.ecommerce.coreservices.kafka.OrderConfirmation;
import com.ecommerce.coreservices.kafka.OrderProducer;
import com.ecommerce.coreservices.orderline.OrderLineRequest;
import com.ecommerce.coreservices.orderline.OrderLineService;
import com.ecommerce.coreservices.payment.PaymentClient;
import com.ecommerce.coreservices.payment.PaymentRequest;
import com.ecommerce.coreservices.product.ProductClient;
import com.ecommerce.coreservices.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        // check the customer --> OpenFeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));

        //purchase the products --> product-ms (RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        //persist order
        var order = this.repository.save(mapper.toOrder(request));
        System.out.println("order id: " + order.getId());
        //persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            var id = orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    ),
                    order
            );
        }
        //start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        this.paymentClient.requestOrderPayment(paymentRequest);
        //send the order confirmation --> notification-ms (kafka_
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
                //this object needs to be serialized to be consumed later on
                //and to deserialize again back to object type of orderconfirmation
        );
        return order.getId();
    }
    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }
    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
