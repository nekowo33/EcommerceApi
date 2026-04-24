package com.ws101.novio.EcommerceApi.exception;

/**
 * Exception thrown when the request data is invalid or incomplete.
 *
 * @author Cosino, Vivian Faith C.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs an InvalidInputException with a descriptive message.
     *
     * @param message the detail message explaining what input was invalid.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
