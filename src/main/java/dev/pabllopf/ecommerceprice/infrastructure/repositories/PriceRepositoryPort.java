package dev.pabllopf.ecommerceprice.infrastructure.repositories;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepositoryPort extends JpaRepository<PriceEntity, Long>, IPriceRepositoryPort {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC LIMIT 1")

    Optional<PriceEntity> findApplicablePriceEntity(@Param("brandId") Integer brandId,
                                                    @Param("productId") Integer productId,
                                                    @Param("date") LocalDateTime date);

    @Override
    default Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return findApplicablePriceEntity(brandId, productId, date).map(PriceEntity::toDomain);
    }
}
