package dev.pabllopf.ecommerceprice.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        Integer brandId = 2;
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        Integer priceList = 3;
        Integer productId = 4;
        Integer priority = 5;
        BigDecimal priceValue = BigDecimal.valueOf(99.99);
        String currency = "USD";

        Price price = new Price(id, brandId, startDate, endDate, priceList, productId, priority, priceValue, currency);

        assertEquals(id, price.getId());
        assertEquals(brandId, price.getBrandId());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(priceList, price.getPriceList());
        assertEquals(productId, price.getProductId());
        assertEquals(priority, price.getPriority());
        assertEquals(priceValue, price.getPrice());
        assertEquals(currency, price.getCurrency());
    }

    @Test
    void testSetters() {
        Price price = new Price(1L, 2, LocalDateTime.of(2023, 1, 1, 0, 0), LocalDateTime.of(2023, 12, 31, 23, 59), 3, 4, 5, BigDecimal.valueOf(99.99), "USD");

        price.setId(10L);
        price.setBrandId(20);
        price.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        price.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        price.setPriceList(30);
        price.setProductId(40);
        price.setPriority(50);
        price.setPrice(BigDecimal.valueOf(199.99));
        price.setCurrency("EUR");

        assertEquals(10L, price.getId());
        assertEquals(20, price.getBrandId());
        assertEquals(LocalDateTime.of(2024, 1, 1, 0, 0), price.getStartDate());
        assertEquals(LocalDateTime.of(2024, 12, 31, 23, 59), price.getEndDate());
        assertEquals(30, price.getPriceList());
        assertEquals(40, price.getProductId());
        assertEquals(50, price.getPriority());
        assertEquals(BigDecimal.valueOf(199.99), price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
}