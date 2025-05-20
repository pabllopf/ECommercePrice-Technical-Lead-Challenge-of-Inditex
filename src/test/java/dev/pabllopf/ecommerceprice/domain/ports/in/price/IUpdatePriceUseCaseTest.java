package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class IUpdatePriceUseCaseTest {

    @Mock
    private IUpdatePriceUseCase updatePriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdatePriceSuccess() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");

        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(100.0));

        assertDoesNotThrow(() -> updatePriceUseCase.updatePrice(price));
        verify(updatePriceUseCase).updatePrice(price);
    }

    @Test
    public void testUpdatePriceFailure() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");

        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(100.0));

        doThrow(new IllegalArgumentException("Price update failed")).when(updatePriceUseCase).updatePrice(price);

        assertThrows(IllegalArgumentException.class, () -> updatePriceUseCase.updatePrice(price));
    }
}