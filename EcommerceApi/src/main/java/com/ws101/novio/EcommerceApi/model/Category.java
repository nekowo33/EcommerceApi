package com.ws101.novio.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Represents a Category in the e-commerce system.
 * A Category has a One-to-Many relationship with Product,
 * meaning one Category can contain multiple Products.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
