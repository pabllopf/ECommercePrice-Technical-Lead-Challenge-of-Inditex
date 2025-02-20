package dev.pabllopf.ecommerceprice.infrastructure.entities;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
@Setter
@Getter
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private Integer priceList;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    public Price toDomain() {
        return new Price(id, brandId, startDate, endDate, priceList, productId, priority, price, currency);
    }

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
}
