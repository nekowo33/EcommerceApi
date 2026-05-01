package com.ws101.novio.EcommerceApi.service;

import com.ws101.novio.EcommerceApi.model.Product;
import com.ws101.novio.EcommerceApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for product-related operations.
 *
 * Manages product data using Spring Data JPA.
 * Provides business logic for CRUD operations and filtering.
 *
 * @author Novio, Mariel Kimberly B.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products from the database.
     *
     * @return a List containing all products.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its unique ID.
     *
     * @param id the unique identifier of the product.
     * @return the Product with the matching ID, or null if not found.
     */
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new product and saves it to the database.
     *
     * @param product the Product object to be saved.
     * @return the newly created Product.
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product by replacing it entirely.
     *
     * @param id the unique identifier of the product to update.
     * @param updatedProduct the new Product data to replace the existing one.
     * @return the updated Product, or null if not found.
     */
    public Product updateProduct(int id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }

    /**
     * Partially updates an existing product.
     *
     * @param id the unique identifier of the product to update.
     * @param updatedProduct the Product object containing fields to update.
     * @return the partially updated Product, or null if not found.
     */
    public Product partialUpdateProduct(int id, Product updatedProduct) {
        Optional<Product> existingProductOpt = productRepository.findById(id);
        if (existingProductOpt.isPresent()) {
            Product product = existingProductOpt.get();
            if (updatedProduct.getName() != null) product.setName(updatedProduct.getName());
            if (updatedProduct.getDescription() != null) product.setDescription(updatedProduct.getDescription());
            if (updatedProduct.getPrice() > 0) product.setPrice(updatedProduct.getPrice());
            if (updatedProduct.getCategory() != null) product.setCategory(updatedProduct.getCategory());
            if (updatedProduct.getStockQuantity() >= 0) product.setStockQuantity(updatedProduct.getStockQuantity());
            if (updatedProduct.getImageUrl() != null) product.setImageUrl(updatedProduct.getImageUrl());
            return productRepository.save(product);
        }
        return null;
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param id the unique identifier of the product to delete.
     * @return true if the product was deleted, false if not found.
     */
    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Filters products by a given filter type and value.
     *
     * @param filterType the criteria to filter by (name, category, price).
     * @param filterValue the value to match against the filter type.
     * @return a List of products matching the filter criteria.
     */
    public List<Product> filterProducts(String filterType, String filterValue) {
        if (filterType.equalsIgnoreCase("name")) {
            return productRepository.findByNameContainingIgnoreCase(filterValue);
        } else if (filterType.equalsIgnoreCase("category")) {
            return productRepository.findByCategoryName(filterValue);
        } else if (filterType.equalsIgnoreCase("price")) {
            try {
                double maxPrice = Double.parseDouble(filterValue);
                return productRepository.findProductsByPriceRange(0, maxPrice);
            } catch (NumberFormatException e) {
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}