package dev.pabllopf.ecommerceprice.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void testPriceFields() {
        Price price = new Price();
        price.setId(1L);
        price.setBrandId(1);
        price.setStartDate(LocalDateTime.of(2025, 2, 20, 10, 0));
        price.setEndDate(LocalDateTime.of(2025, 2, 20, 20, 0));
        price.setPriceList(1);
        price.setProductId(100);
        price.setPriority(1);
        price.setPrice(new BigDecimal("100.00"));
        price.setCurrency("USD");

        assertEquals(1L, price.getId());
        assertEquals(1, price.getBrandId());
        assertEquals(LocalDateTime.of(2025, 2, 20, 10, 0), price.getStartDate());
        assertEquals(LocalDateTime.of(2025, 2, 20, 20, 0), price.getEndDate());
        assertEquals(1, price.getPriceList());
        assertEquals(100, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(new BigDecimal("100.00"), price.getPrice());
        assertEquals("USD", price.getCurrency());
    }

    @Test
    void testPriceInequality() {
        Price price1 = new Price();
        price1.setId(1L);
        price1.setBrandId(1);
        price1.setStartDate(LocalDateTime.of(2025, 2, 20, 10, 0));
        price1.setEndDate(LocalDateTime.of(2025, 2, 20, 20, 0));
        price1.setPriceList(1);
        price1.setProductId(100);
        price1.setPriority(1);
        price1.setPrice(new BigDecimal("100.00"));
        price1.setCurrency("USD");

        Price price2 = new Price();
        price2.setId(2L);
        price2.setBrandId(2);
        price2.setStartDate(LocalDateTime.of(2025, 2, 21, 10, 0));
        price2.setEndDate(LocalDateTime.of(2025, 2, 21, 20, 0));
        price2.setPriceList(2);
        price2.setProductId(101);
        price2.setPriority(2);
        price2.setPrice(new BigDecimal("200.00"));
        price2.setCurrency("EUR");

        assertNotEquals(price1, price2);
    }
}