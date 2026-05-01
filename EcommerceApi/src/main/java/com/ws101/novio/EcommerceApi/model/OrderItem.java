package com.ws101.novio.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an OrderItem in the e-commerce system.
 * An OrderItem has a Many-to-One relationship with Order (many items belong to one order).
 * It also has a Many-to-One relationship with Product (many items can refer to the same product).
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
