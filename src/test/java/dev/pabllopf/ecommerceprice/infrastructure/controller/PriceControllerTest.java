package dev.pabllopf.ecommerceprice.infrastructure.controller;

import dev.pabllopf.ecommerceprice.domain.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 100;
    private static final String DATE = "2025-02-20T10:00:00";

    @BeforeEach
    void setUp() {
        // Configuración inicial antes de cada test, si es necesario
    }

    @Test
    void testGetPrice_WhenPriceNotFound() {
        // Arrange: Simula que el servicio no encuentra el precio
        LocalDateTime dateTime = LocalDateTime.parse(DATE);
        when(priceService.getPrice(BRAND_ID, PRODUCT_ID, dateTime)).thenReturn(Optional.empty());

        // Act & Assert: Verifica que se lance la excepción correcta
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            priceController.getPrice(BRAND_ID, PRODUCT_ID, DATE);
        });
        assertEquals("Price not found", thrown.getMessage());
    }

    @Test
    void testGetPrice_WhenInvalidDateFormat() {
        // Act & Assert: Verifica que se lance la excepción cuando el formato de la fecha es inválido
        String invalidDate = "invalid-date";
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            priceController.getPrice(BRAND_ID, PRODUCT_ID, invalidDate);
        });
    }

    @Test
    void testGetPrice_WhenDateIsNull() {
        // Arrange: Configura un caso con fecha nula
        String nullDate = null;
        assertThrows(NullPointerException.class, () -> {
            priceController.getPrice(BRAND_ID, PRODUCT_ID, nullDate);
        });
    }

    @Test
    void testGetPrice_WhenPriceServiceThrowsException() {
        // Arrange: Configura el servicio para que lance una excepción
        LocalDateTime dateTime = LocalDateTime.parse(DATE);
        when(priceService.getPrice(BRAND_ID, PRODUCT_ID, dateTime)).thenThrow(new RuntimeException("Internal Server Error"));

        // Act & Assert: Verifica que la excepción lanzada es la esperada
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            priceController.getPrice(BRAND_ID, PRODUCT_ID, DATE);
        });
        assertEquals("Internal Server Error", thrown.getMessage());
    }
}
