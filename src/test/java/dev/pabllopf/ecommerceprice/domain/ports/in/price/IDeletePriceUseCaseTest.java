package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

public class IDeletePriceUseCaseTest {

    @Mock
    private IDeletePriceUseCase deletePriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletePriceSuccess() {
        Long priceId = 1L;

        deletePriceUseCase.deletePrice(priceId);

    }

    @Test
    public void testDeletePriceNotFound() {
        Long priceId = 1L;

        doThrow(new IllegalArgumentException("Price not found")).when(deletePriceUseCase).deletePrice(priceId);

        assertThrows(IllegalArgumentException.class, () -> deletePriceUseCase.deletePrice(priceId));
    }
}