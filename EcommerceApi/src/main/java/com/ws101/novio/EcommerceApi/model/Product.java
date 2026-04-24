package com.ws101.novio.EcommerceApi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * Represents a product in the e-commerce system.
 *
 * @author Novio, Mariel Kimberly B.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    /** Unique identifier for the product. */
    private int id;

    /** Name of the product. */
    private String name;

    /** Description of the product. */
    private String description;

    /** Price of the product. */
    private double price;

    /** Category the product belongs to. */
    private String category;

    /** Number of items available in stock. */
    private int stockQuantity;

    /** URL of the product image. */
    private String imageUrl;
}