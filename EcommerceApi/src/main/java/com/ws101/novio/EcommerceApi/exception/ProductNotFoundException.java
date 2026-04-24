package com.ws101.novio.EcommerceApi.exception;

/**
 * Exception thrown when a requested product is not found in the catalog.
 *
 * @author Cosino, Vivian Faith C.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a ProductNotFoundException with a descriptive message.
     *
     * @param message the detail message explaining which product was not found.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
