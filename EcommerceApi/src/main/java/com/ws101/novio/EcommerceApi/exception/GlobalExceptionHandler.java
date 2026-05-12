package com.ws101.novio.EcommerceApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the e-commerce API.
 *
 * Catches exceptions thrown across all controllers and returns
 * consistent error responses with appropriate HTTP status codes.
 *
 * @author Cosino, Vivian Faith C.
 * @see ErrorResponse
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ProductNotFoundException and returns a 404 Not Found response.
     *
     * @param ex the ProductNotFoundException that was thrown.
     * @return ResponseEntity containing the error details with 404 status.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles InvalidInputException and returns a 400 Bad Request response.
     *
     * @param ex the InvalidInputException that was thrown.
     * @return ResponseEntity containing the error details with 400 status.
     */
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidInputException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles any unexpected exceptions and returns a 500 Internal Server Error.
     *
     * @param ex the exception that was thrown.
     * @return ResponseEntity containing the error details with 500 status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles MethodArgumentNotValidException thrown when @Valid fails.
     * Returns a 400 Bad Request with specific validation error messages.
     *
     * @param ex the MethodArgumentNotValidException that was thrown.
     * @return ResponseEntity containing the validation errors with 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Collect all validation error messages into a list
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "Field '" + error.getField() + "': " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                errors.toString()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
