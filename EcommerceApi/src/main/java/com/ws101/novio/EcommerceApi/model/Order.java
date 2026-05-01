package com.ws101.novio.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Represents an Order in the e-commerce system.
 * An Order has a One-to-Many relationship with OrderItem,
 * meaning one Order can contain multiple OrderItems.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orderItems")
@EqualsAndHashCode(exclude = "orderItems")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerName;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
