package dev.pabllopf.ecommerceprice.application.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceResponseDtoTest {

    @Test
    public void testPriceResponseDtoConstructorAndGetters() {
        Integer productId = 1;
        Integer brandId = 2;
        Integer priceList = 3;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);
        BigDecimal price = new BigDecimal("100.00");

        PriceResponseDto dto = new PriceResponseDto(productId, brandId, priceList, startDate, endDate, price);

        assertEquals(productId, dto.getProductId());
        assertEquals(brandId, dto.getBrandId());
        assertEquals(priceList, dto.getPriceList());
        assertEquals(startDate, dto.getStartDate());
        assertEquals(endDate, dto.getEndDate());
        assertEquals(price, dto.getPrice());
    }

    @Test
    public void testPriceResponseDtoSetters() {
        PriceResponseDto dto = new PriceResponseDto(1, 2, 3, LocalDateTime.now(), LocalDateTime.now().plusDays(1), new BigDecimal("100.00"));

        Integer newProductId = 10;
        Integer newBrandId = 20;
        Integer newPriceList = 30;
        LocalDateTime newStartDate = LocalDateTime.now().plusDays(2);
        LocalDateTime newEndDate = LocalDateTime.now().plusDays(3);
        BigDecimal newPrice = new BigDecimal("200.00");

        dto.setProductId(newProductId);
        dto.setBrandId(newBrandId);
        dto.setPriceList(newPriceList);
        dto.setStartDate(newStartDate);
        dto.setEndDate(newEndDate);
        dto.setPrice(newPrice);

        assertEquals(newProductId, dto.getProductId());
        assertEquals(newBrandId, dto.getBrandId());
        assertEquals(newPriceList, dto.getPriceList());
        assertEquals(newStartDate, dto.getStartDate());
        assertEquals(newEndDate, dto.getEndDate());
        assertEquals(newPrice, dto.getPrice());
    }
}