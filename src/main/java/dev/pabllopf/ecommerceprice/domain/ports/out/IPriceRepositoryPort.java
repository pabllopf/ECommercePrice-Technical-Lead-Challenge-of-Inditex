package dev.pabllopf.ecommerceprice.domain.ports.out;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IPriceRepositoryPort {
    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);
}
