package dev.pabllopf.ecommerceprice.domain.ports.out;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPriceRepositoryPort {

    Price save(Price price);

    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);

    Optional<Price> findById(Long id);

    List<Price> findAll();

    Optional<Price> update(Price price);

    boolean deleteById(Long id);
}
