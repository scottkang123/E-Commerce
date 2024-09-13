package com.ecommerce.coreservices.orderline;

import com.ecommerce.coreservices.order.Order;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id") //comes from id field of order
    private Order order;

    private Integer productId; //references from the product db
    private double quantity;
}
