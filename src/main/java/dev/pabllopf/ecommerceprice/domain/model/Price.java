package dev.pabllopf.ecommerceprice.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a pricing entity in the e-commerce system.
 * This class models the price information for a product,
 * including validity periods, priority, and currency.
 */
@Setter
@Getter
public class Price {

    /** Unique identifier for the price record. */
    private Long id;

    /** Identifier for the brand associated with this price. */
    private Integer brandId;

    /** Start date and time when the price becomes effective. */
    private LocalDateTime startDate;

    /** End date and time when the price is no longer valid. */
    private LocalDateTime endDate;

    /** Identifier for the price list. */
    private Integer priceList;

    /** Identifier for the product this price applies to. */
    private Integer productId;

    /** Priority of the price in case multiple prices overlap. Higher priority takes precedence. */
    private Integer priority;

    /** Price value for the product. */
    private BigDecimal price;

    /** Currency in which the price is expressed (e.g., "USD", "EUR"). */
    private String currency;

    /**
     * Constructs a new Price object with all necessary attributes.
     *
     * @param id        Unique identifier for the price.
     * @param brandId   Brand ID associated with the price.
     * @param startDate Start date and time of the price validity.
     * @param endDate   End date and time of the price validity.
     * @param priceList Price list identifier.
     * @param productId Product ID related to the price.
     * @param priority  Priority level of the price.
     * @param price     Price amount.
     * @param currency  Currency of the price.
     */
    public Price(Long id, Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList,
                 Integer productId, Integer priority, BigDecimal price, String currency) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }
}
