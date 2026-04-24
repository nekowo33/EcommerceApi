package com.ws101.novio.EcommerceApi.service;

import com.ws101.novio.EcommerceApi.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for product-related operations.
 *
 * Manages product data using in-memory storage with a List.
 * Provides business logic for CRUD operations and filtering.
 *
 * @author Novio, Mariel Kimberly B.
 */
public class ProductService {

    // In-memory storage for products using ArrayList
    private List<Product> productList = new ArrayList<>();

    // Counter for generating unique IDs
    private int idCounter = 1;

    /**
     * Constructor that initializes the product list with sample data.
     */
    public ProductService() {
        initializeSampleData();
    }

    /**
     * Initializes the product list with 10 sample products.
     */
    private void initializeSampleData() {
        productList.add(new Product(idCounter++, "Roses Bouquet", "A classic bouquet of fresh red roses.", 1200.00, "Flowers", 50, "https://i.pinimg.com/736x/5a/aa/d2/5aaad243a6cc3e7e3c1f6669b50b260d.jpg"));
        productList.add(new Product(idCounter++, "Tulips Bouquet", "A beautiful bouquet of fresh tulips.", 1800.00, "Flowers", 40, "https://i.pinimg.com/736x/f8/6c/7b/f86c7b3f61c7b5409d462498f05bb11b.jpg"));
        productList.add(new Product(idCounter++, "Lilies Bouquet", "A stunning bouquet of fresh lilies.", 2000.00, "Flowers", 30, "https://i.pinimg.com/736x/98/4a/a6/984aa6c4765025d0abac3a00fae15d0d.jpg"));
        productList.add(new Product(idCounter++, "Sunflower Bouquet", "Bright and cheerful sunflower bouquet.", 1500.00, "Flowers", 35, "https://i.pinimg.com/1200x/e7/74/ac/e774ace7f45a08a7f1573d42a0824dc0.jpg"));
        productList.add(new Product(idCounter++, "Daisy Bouquet", "A sweet and simple daisy bouquet.", 900.00, "Flowers", 60, "https://i.pinimg.com/1200x/e1/dd/06/e1dd06c81bde52961dfa25c2c9061fc1.jpg"));
        productList.add(new Product(idCounter++, "Orchid Bouquet", "An elegant bouquet of fresh orchids.", 2500.00, "Flowers", 20, "https://i.pinimg.com/736x/5a/83/0f/5a830f1e2f75fc3bc3a8151b8918670e.jpg"));
        productList.add(new Product(idCounter++, "Peony Bouquet", "A romantic bouquet of fresh peonies.", 2200.00, "Flowers", 25, "https://i.pinimg.com/736x/c4/cf/a6/c4cfa6cf64212db4966cd5d1b801288c.jpg"));
        productList.add(new Product(idCounter++, "Lavender Bouquet", "A calming bouquet of fresh lavender.", 1300.00, "Flowers", 45, "https://i.pinimg.com/1200x/19/e1/7b/19e17b26ed6a194a3d5f8bbcfe94a31e.jpg"));
        productList.add(new Product(idCounter++, "Mixed Bouquet", "A colorful mix of seasonal flowers.", 1700.00, "Flowers", 55, "https://i.pinimg.com/1200x/47/c5/e0/47c5e08d73d917717ec2565f4960724b.jpg"));
        productList.add(new Product(idCounter++, "Carnation Bouquet", "A delicate bouquet of fresh carnations.", 1300.00, "Flowers", 50, "https://i.pinimg.com/736x/7f/e0/d5/7fe0d5ac477b225b9602368357690027.jpg"));
    }

    /**
     * Retrieves all products from the list.
     *
     * @return a List containing all products.
     */
    public List<Product> getAllProducts() {
        return productList;
    }

    /**
     * Finds a product by its unique ID.
     *
     * @param id the unique identifier of the product.
     * @return the Product with the matching ID, or null if not found.
     */
    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * Creates a new product and adds it to the list.
     *
     * @param product the Product object to be added.
     * @return the newly created Product with its assigned ID.
     */
    public Product createProduct(Product product) {
        // Assign a unique ID using the counter
        product.setId(idCounter++);
        productList.add(product);
        return product;
    }

    /**
     * Updates an existing product by replacing it entirely.
     *
     * @param id the unique identifier of the product to update.
     * @param updatedProduct the new Product data to replace the existing one.
     * @return the updated Product, or null if not found.
     */
    public Product updateProduct(int id, Product updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                updatedProduct.setId(id);
                productList.set(i, updatedProduct);
                return updatedProduct;
            }
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
        for (Product product : productList) {
            if (product.getId() == id) {
                if (updatedProduct.getName() != null) product.setName(updatedProduct.getName());
                if (updatedProduct.getDescription() != null) product.setDescription(updatedProduct.getDescription());
                if (updatedProduct.getPrice() > 0) product.setPrice(updatedProduct.getPrice());
                if (updatedProduct.getCategory() != null) product.setCategory(updatedProduct.getCategory());
                if (updatedProduct.getStockQuantity() >= 0) product.setStockQuantity(updatedProduct.getStockQuantity());
                if (updatedProduct.getImageUrl() != null) product.setImageUrl(updatedProduct.getImageUrl());
                return product;
            }
        }
        return null;
    }

    /**
     * Deletes a product from the list by its ID.
     *
     * @param id the unique identifier of the product to delete.
     * @return true if the product was deleted, false if not found.
     */
    public boolean deleteProduct(int id) {
        return productList.removeIf(product -> product.getId() == id);
    }

    /**
     * Filters products by a given filter type and value.
     *
     * @param filterType the criteria to filter by (name, category, price).
     * @param filterValue the value to match against the filter type.
     * @return a List of products matching the filter criteria.
     */
    public List<Product> filterProducts(String filterType, String filterValue) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : productList) {
            if (filterType.equalsIgnoreCase("name") &&
                    product.getName().toLowerCase().contains(filterValue.toLowerCase())) {
                filteredList.add(product);
            } else if (filterType.equalsIgnoreCase("category") &&
                    product.getCategory().equalsIgnoreCase(filterValue)) {
                filteredList.add(product);
            } else if (filterType.equalsIgnoreCase("price") &&
                    product.getPrice() <= Double.parseDouble(filterValue)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }
}