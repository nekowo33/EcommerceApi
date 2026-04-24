package com.ws101.novio.EcommerceApi.controller;

import com.ws101.novio.EcommerceApi.exception.InvalidInputException;
import com.ws101.novio.EcommerceApi.exception.ProductNotFoundException;
import com.ws101.novio.EcommerceApi.model.Product;
import com.ws101.novio.EcommerceApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products in the e-commerce API.
 *
 * Handles HTTP requests and delegates business logic to the ProductService.
 * This controller maps to the /api/v1/products base path and performs
 * basic input validation before passing data to the service layer.
 *
 * @author Cosino, Vivian Faith C.
 * @see ProductService
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products from the catalog.
     *
     * @return ResponseEntity containing a list of all products with 200 OK status.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Retrieves a single product by its unique ID.
     *
     * @param id the unique identifier of the product to retrieve.
     * @return ResponseEntity containing the product with 200 OK status.
     * @throws ProductNotFoundException if no product exists with the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Filters products by a specified criteria and value.
     *
     * @param filterType the criteria to filter by (e.g., name, category, price).
     * @param filterValue the value to match against the filter type.
     * @return ResponseEntity containing a list of matching products with 200 OK.
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        List<Product> filteredProducts = productService.filterProducts(filterType, filterValue);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    /**
     * Creates a new product in the catalog.
     *
     * @param product the product data received from the request body.
     * @return ResponseEntity containing the created product with 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        validateProduct(product);
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Replaces an existing product entirely with new data (PUT).
     *
     * @param id the unique identifier of the product to replace.
     * @param product the new product data to replace the existing one.
     * @return ResponseEntity containing the updated product with 200 OK status.
     * @throws ProductNotFoundException if no product exists with the given ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        validateProduct(product);
        Product updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Partially updates an existing product with the provided fields (PATCH).
     *
     * @param id the unique identifier of the product to update.
     * @param product the product data containing only the fields to update.
     * @return ResponseEntity containing the updated product with 200 OK status.
     * @throws ProductNotFoundException if no product exists with the given ID.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Product> partialUpdateProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productService.partialUpdateProduct(id, product);

        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Removes a product from the catalog by its ID.
     *
     * @param id the unique identifier of the product to delete.
     * @return ResponseEntity with 204 No Content if successfully deleted.
     * @throws ProductNotFoundException if no product exists with the given ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);

        if (!deleted) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Validates the product data before creating or updating.
     *
     * Checks that required fields are present and values are within
     * acceptable ranges. Throws InvalidInputException if validation fails.
     *
     * @param product the Product to validate.
     * @throws InvalidInputException if any field fails validation.
     */
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().length() < 2) {
            throw new InvalidInputException("Product name is required and must be at least 2 characters.");
        }
        if (product.getPrice() <= 0) {
            throw new InvalidInputException("Product price must be a positive number.");
        }
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            throw new InvalidInputException("Product category is required.");
        }
        if (product.getStockQuantity() < 0) {
            throw new InvalidInputException("Stock quantity must be non-negative.");
        }
    }
}
