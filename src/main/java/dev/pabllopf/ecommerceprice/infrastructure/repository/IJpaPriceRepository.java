package dev.pabllopf.ecommerceprice.infrastructure.repository;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.repository.IPriceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IJpaPriceRepository extends JpaRepository<Price, Long>, IPriceRepository {

    @Override
    default Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId, productId, date, date);
    }

    Optional<Price> findTopByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Integer brandId, Integer productId, LocalDateTime start, LocalDateTime end);
}
