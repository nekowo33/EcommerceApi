package com.ws101.novio.EcommerceApi.exception;

import java.time.LocalDateTime;

/**
 * Standard error response format for the API.
 *
 * Provides a consistent structure for all error responses,
 * including a timestamp, HTTP status code, and descriptive message.
 *
 * @author Cosino, Vivian Faith C.
 */
public class ErrorResponse {

    /** Timestamp of when the error occurred. */
    private LocalDateTime timestamp;

    /** HTTP status code of the error. */
    private int status;

    /** Short description of the error type. */
    private String error;

    /** Detailed message describing what went wrong. */
    private String message;

    /**
     * Constructs an ErrorResponse with the given details.
     *
     * @param status the HTTP status code.
     * @param error a short error type description.
     * @param message a detailed error message.
     */
    public ErrorResponse(int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getters and setters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
