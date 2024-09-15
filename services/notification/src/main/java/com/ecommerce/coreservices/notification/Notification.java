package com.ecommerce.coreservices.notification;

import com.ecommerce.coreservices.kafka.order.OrderConfirmation;
import com.ecommerce.coreservices.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
    //since NoSQL database can just include object as it is
}
