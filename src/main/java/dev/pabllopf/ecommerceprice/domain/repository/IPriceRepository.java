package dev.pabllopf.ecommerceprice.domain.repository;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IPriceRepository {
    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);
}
