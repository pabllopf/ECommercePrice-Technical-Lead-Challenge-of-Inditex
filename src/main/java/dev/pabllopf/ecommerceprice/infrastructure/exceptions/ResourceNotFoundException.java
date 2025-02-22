package dev.pabllopf.ecommerceprice.infrastructure.exceptions;

/**
 * Custom exception to be thrown when a requested resource is not found.
 * This exception is used to provide meaningful error messages when
 * the desired resource does not exist or cannot be retrieved.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     * The message will be passed to the parent {@link RuntimeException} class.
     *
     * @param message The detail message that describes the cause of the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);  // Pass the message to the superclass (RuntimeException)
    }
}
