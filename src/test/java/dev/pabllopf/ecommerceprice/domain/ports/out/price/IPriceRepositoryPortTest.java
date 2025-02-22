package dev.pabllopf.ecommerceprice.domain.ports.out.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPriceRepositoryPortTest {

    @Mock
    private IPriceRepositoryPort priceRepositoryPort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");
        when(priceRepositoryPort.save(price)).thenReturn(price);

        Price savedPrice = priceRepositoryPort.save(price);
        assertNotNull(savedPrice);
        assertEquals(price, savedPrice);
    }

    @Test
    public void testFindApplicablePrice() {
        Integer brandId = 1;
        Integer productId = 1;
        LocalDateTime date = LocalDateTime.now();
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");
        
        when(priceRepositoryPort.findApplicablePrice(brandId, productId, date)).thenReturn(Optional.of(price));

        Optional<Price> foundPrice = priceRepositoryPort.findApplicablePrice(brandId, productId, date);
        assertTrue(foundPrice.isPresent());
        assertEquals(price, foundPrice.get());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");
        
        when(priceRepositoryPort.findById(id)).thenReturn(Optional.of(price));

        Optional<Price> foundPrice = priceRepositoryPort.findById(id);
        assertTrue(foundPrice.isPresent());
        assertEquals(price, foundPrice.get());
    }

    @Test
    public void testFindAll() {
        List<Price> prices = List.of(new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD")
        , new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD"));
        when(priceRepositoryPort.findAll()).thenReturn(prices);

        List<Price> foundPrices = priceRepositoryPort.findAll();
        assertNotNull(foundPrices);
        assertEquals(prices.size(), foundPrices.size());
    }

    @Test
    public void testUpdate() {
        Price price = new Price(1L, 1,  LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2,new BigDecimal(2),"USD");
        
        when(priceRepositoryPort.update(price)).thenReturn(Optional.of(price));

        Optional<Price> updatedPrice = priceRepositoryPort.update(price);
        assertTrue(updatedPrice.isPresent());
        assertEquals(price, updatedPrice.get());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(priceRepositoryPort.deleteById(id)).thenReturn(true);

        boolean isDeleted = priceRepositoryPort.deleteById(id);
        assertTrue(isDeleted);
    }
}