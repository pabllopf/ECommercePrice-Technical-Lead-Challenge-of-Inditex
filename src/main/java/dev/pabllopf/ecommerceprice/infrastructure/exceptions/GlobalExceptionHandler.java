package dev.pabllopf.ecommerceprice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a centralized exception handler for all exceptions
 * in the application. It intercepts exceptions and returns appropriate responses
 * to the client in a consistent format.
 * This class provides custom handling for ResourceNotFoundException and a fallback
 * for other generic exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the ResourceNotFoundException and returns a custom response.
     * This exception is typically thrown when a resource (e.g., a product, price, etc.)
     * is not found in the system.
     *
     * @param ex The exception instance containing the error details.
     * @return A ResponseEntity containing a custom error message and a 404 status code.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());  // Add timestamp of the error occurrence.
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());  // HTTP status code for "Not Found".
        errorResponse.put("error", "Resource Not Found");  // Error description.
        errorResponse.put("message", ex.getMessage());  // Detailed error message.

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);  // Return 404 with error details.
    }

    /**
     * Handles generic exceptions that are not explicitly handled elsewhere.
     * This is a fallback handler to catch unforeseen errors that could occur
     * during the execution of the application.
     *
     * @param ex The exception instance containing the error details.
     * @return A ResponseEntity containing a generic error message and a 500 status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());  // Add timestamp of the error occurrence.
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());  // HTTP status code for "Internal Server Error".
        errorResponse.put("error", "Internal Server Error");  // Error description.
        errorResponse.put("message", ex.getMessage());  // Detailed error message.

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);  // Return 500 with error details.
    }
}
