package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IGetPriceUseCase {

    List<Price> findAllPrices();

    Optional<Price> findPriceById(Long id);

    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);
}
