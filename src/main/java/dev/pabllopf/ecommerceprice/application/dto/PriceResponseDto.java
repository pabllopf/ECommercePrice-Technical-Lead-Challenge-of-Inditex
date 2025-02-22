package dev.pabllopf.ecommerceprice.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing the price information.
 * This DTO is used to send price-related data in API responses.
 * It encapsulates the data needed to return detailed price information for a specific product.
 */
@Setter
@Getter
public class PriceResponseDto {

    private Integer productId;  // ID of the product
    private Integer brandId;    // ID of the brand
    private Integer priceList;  // ID of the price list
    private LocalDateTime startDate; // Start date of the price validity period
    private LocalDateTime endDate;   // End date of the price validity period
    private BigDecimal price;        // Price of the product

    /**
     * Constructor to initialize the PriceResponseDto with all the necessary data.
     *
     * @param productId The ID of the product.
     * @param brandId The ID of the brand.
     * @param priceList The ID of the price list.
     * @param startDate The start date of the price validity period.
     * @param endDate The end date of the price validity period.
     * @param price The price of the product.
     */
    public PriceResponseDto(Integer productId, Integer brandId, Integer priceList,
                            LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        this.productId = productId;  // Set product ID
        this.brandId = brandId;      // Set brand ID
        this.priceList = priceList;  // Set price list ID
        this.startDate = startDate;  // Set start date
        this.endDate = endDate;      // Set end date
        this.price = price;          // Set price
    }
}
