package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ICreatePriceUseCaseTest {

    @Mock
    private ICreatePriceUseCase createPriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePriceSuccess() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1, new BigDecimal(1), "USD");

        when(createPriceUseCase.createPrice(price)).thenReturn(price);

        Price createdPrice = createPriceUseCase.createPrice(price);
        assertNotNull(createdPrice);
        assertEquals(price.getId(), createdPrice.getId());
        assertEquals(price.getPrice(), createdPrice.getPrice());
    }
}