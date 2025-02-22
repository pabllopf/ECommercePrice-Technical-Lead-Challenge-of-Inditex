package dev.pabllopf.ecommerceprice.infrastructure.entities;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceEntityTest {

    @Test
    void testGettersAndSetters() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1L);
        priceEntity.setBrandId(2);
        priceEntity.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        priceEntity.setPriceList(3);
        priceEntity.setProductId(4);
        priceEntity.setPriority(5);
        priceEntity.setPrice(BigDecimal.valueOf(99.99));
        priceEntity.setCurrency("USD");

        assertEquals(1L, priceEntity.getId());
        assertEquals(2, priceEntity.getBrandId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), priceEntity.getStartDate());
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), priceEntity.getEndDate());
        assertEquals(3, priceEntity.getPriceList());
        assertEquals(4, priceEntity.getProductId());
        assertEquals(5, priceEntity.getPriority());
        assertEquals(BigDecimal.valueOf(99.99), priceEntity.getPrice());
        assertEquals("USD", priceEntity.getCurrency());
    }

    @Test
    void testToDomain() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1L);
        priceEntity.setBrandId(2);
        priceEntity.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        priceEntity.setPriceList(3);
        priceEntity.setProductId(4);
        priceEntity.setPriority(5);
        priceEntity.setPrice(BigDecimal.valueOf(99.99));
        priceEntity.setCurrency("USD");

        Price price = priceEntity.toDomain();

        assertEquals(1L, price.getId());
        assertEquals(2, price.getBrandId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), price.getStartDate());
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), price.getEndDate());
        assertEquals(3, price.getPriceList());
        assertEquals(4, price.getProductId());
        assertEquals(5, price.getPriority());
        assertEquals(BigDecimal.valueOf(99.99), price.getPrice());
        assertEquals("USD", price.getCurrency());
    }

    @Test
    void testFromDomain() {
        Price price = new Price(1L, 2, LocalDateTime.of(2023, 1, 1, 0, 0), LocalDateTime.of(2023, 12, 31, 23, 59), 3, 4, 5, BigDecimal.valueOf(99.99), "USD");

        PriceEntity priceEntity = PriceEntity.fromDomain(price);

        assertEquals(1L, priceEntity.getId());
        assertEquals(2, priceEntity.getBrandId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 0, 0), priceEntity.getStartDate());
        assertEquals(LocalDateTime.of(2023, 12, 31, 23, 59), priceEntity.getEndDate());
        assertEquals(3, priceEntity.getPriceList());
        assertEquals(4, priceEntity.getProductId());
        assertEquals(5, priceEntity.getPriority());
        assertEquals(BigDecimal.valueOf(99.99), priceEntity.getPrice());
        assertEquals("USD", priceEntity.getCurrency());
    }
}