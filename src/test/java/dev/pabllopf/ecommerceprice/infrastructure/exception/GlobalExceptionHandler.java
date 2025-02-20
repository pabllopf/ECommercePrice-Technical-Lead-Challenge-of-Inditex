package dev.pabllopf.ecommerceprice.infrastructure.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleResourceNotFoundException_ShouldReturnNotFoundResponse() {
        // Given
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleResourceNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().get("status"));
        assertEquals("Resource Not Found", response.getBody().get("error"));
        assertEquals("Resource not found", response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp")); // Verifica que el timestamp se genera
    }

    @Test
    void handleGenericException_ShouldReturnInternalServerErrorResponse() {
        // Given
        Exception exception = new Exception("Unexpected error");

        // When
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleGenericException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(500, response.getBody().get("status"));
        assertEquals("Internal Server Error", response.getBody().get("error"));
        assertEquals("Unexpected error", response.getBody().get("message"));
        assertNotNull(response.getBody().get("timestamp")); // Verifica que el timestamp se genera
    }
}
