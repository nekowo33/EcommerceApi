package com.ws101.novio.EcommerceApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * Represents a product in the e-commerce system.
 * A Product has a Many-to-One relationship with Category,
 * meaning many Products can belong to one Category.
 *
 * @author Novio, Mariel Kimberly B.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "category")
@EqualsAndHashCode(exclude = "category")
public class Product {

    /** Unique identifier for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Name of the product. */
    private String name;

    /** Description of the product. */
    private String description;

    /** Price of the product. */
    private double price;

    /** Category the product belongs to. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /** Number of items available in stock. */
    private int stockQuantity;

    /** URL of the product image. */
    private String imageUrl;
}