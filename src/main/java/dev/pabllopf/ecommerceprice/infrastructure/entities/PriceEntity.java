package dev.pabllopf.ecommerceprice.infrastructure.entities;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * PriceEntity represents the database entity for price-related data.
 * It is mapped to the "prices" table and contains all the necessary fields for a price record.
 * The class provides conversion methods between the entity and domain model for easier integration in the service layer.
 */
@Entity
@Table(name = "prices")
@Setter
@Getter
public class PriceEntity {

    /**
     * The unique identifier for the price record.
     * This field is automatically generated and is used to reference the price entity in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The brand ID associated with the price record.
     * This is used to link the price to a particular brand.
     */
    @Column(name = "brand_id")
    private Integer brandId;

    /**
     * The start date and time when the price is applicable.
     * This defines the beginning of the price validity period.
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * The end date and time when the price is no longer applicable.
     * This defines the end of the price validity period.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * The price list ID associated with the price record.
     * This is used to identify the specific price list the price belongs to.
     */
    @Column(name = "price_list")
    private Integer priceList;

    /**
     * The product ID associated with the price record.
     * This links the price to a specific product.
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * The priority of the price record.
     * This is used to determine the order of prices when there are multiple applicable prices.
     */
    @Column(name = "priority")
    private Integer priority;

    /**
     * The price value.
     * This is the actual price of the product.
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * The currency of the price value.
     * This specifies the currency in which the price is denominated (e.g., EUR, USD).
     */
    @Column(name = "currency")
    private String currency;

    /**
     * Converts a domain model (Price) to a PriceEntity for persistence in the database.
     *
     * @param price The domain model to be converted.
     * @return A PriceEntity representing the provided domain model.
     */
    public static PriceEntity fromDomain(Price price) {
        PriceEntity entity = new PriceEntity();
        entity.setId(price.getId());
        entity.setBrandId(price.getBrandId());
        entity.setStartDate(price.getStartDate());
        entity.setEndDate(price.getEndDate());
        entity.setPriceList(price.getPriceList());
        entity.setProductId(price.getProductId());
        entity.setPriority(price.getPriority());
        entity.setPrice(price.getPrice());
        entity.setCurrency(price.getCurrency());
        return entity;
    }

    /**
     * Converts this entity to its corresponding domain model.
     * The domain model is used in the application logic and service layer.
     *
     * @return A Price object representing the entity data.
     */
    public Price toDomain() {
        return new Price(id, brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }
}
