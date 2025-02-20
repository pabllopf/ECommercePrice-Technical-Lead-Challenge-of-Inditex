package dev.pabllopf.ecommerceprice.domain.model;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class Price {
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;

    public Price(Long id, Integer brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Integer productId, Integer priority, BigDecimal price, String currency) {

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
