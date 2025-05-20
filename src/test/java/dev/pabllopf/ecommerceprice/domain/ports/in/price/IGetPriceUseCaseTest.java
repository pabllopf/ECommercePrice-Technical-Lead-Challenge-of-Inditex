package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class IGetPriceUseCaseTest {

    @Mock
    private IGetPriceUseCase getPriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllPrices() {
        List<Price> foundPrices = getPriceUseCase.findAllPrices();
        assertNotNull(foundPrices);
        assertEquals(0, foundPrices.size());
    }

    @Test
    public void testFindPriceById() {
        Long id = 1L;
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");

        when(getPriceUseCase.findPriceById(id)).thenReturn(Optional.of(price));

        Optional<Price> foundPrice = getPriceUseCase.findPriceById(id);
        assertTrue(foundPrice.isPresent());
        assertEquals(price, foundPrice.get());
    }

    @Test
    public void testFindPriceByIdNotFound() {
        Long id = 1L;
        when(getPriceUseCase.findPriceById(id)).thenReturn(Optional.empty());

        Optional<Price> foundPrice = getPriceUseCase.findPriceById(id);
        assertTrue(foundPrice.isEmpty());
    }

    @Test
    public void testFindApplicablePrice() {
        Integer brandId = 1;
        Integer productId = 1;
        LocalDateTime date = LocalDateTime.now();
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");
        when(getPriceUseCase.findApplicablePrice(brandId, productId, date)).thenReturn(Optional.of(price));

        Optional<Price> foundPrice = getPriceUseCase.findApplicablePrice(brandId, productId, date);
        assertTrue(foundPrice.isPresent());
        assertEquals(price, foundPrice.get());
    }

    @Test
    public void testFindApplicablePriceNotFound() {
        Integer brandId = 1;
        Integer productId = 1;
        LocalDateTime date = LocalDateTime.now();
        when(getPriceUseCase.findApplicablePrice(brandId, productId, date)).thenReturn(Optional.empty());

        Optional<Price> foundPrice = getPriceUseCase.findApplicablePrice(brandId, productId, date);
        assertTrue(foundPrice.isEmpty());
    }
}