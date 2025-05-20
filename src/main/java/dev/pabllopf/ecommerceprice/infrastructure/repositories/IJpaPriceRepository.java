package dev.pabllopf.ecommerceprice.infrastructure.repositories;

import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * PriceRepository is the adapter that handles the persistence of prices in the database.
 * It implements the IPriceRepositoryPort interface, which defines the operations required by the domain layer.
 */
public interface IJpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    /**
     * Finds the applicable price for a specific brand, product, and date.
     * This method searches the database to get the applicable price based on priority and date range.
     *
     * @param brandId   The ID of the brand.
     * @param productId The ID of the product.
     * @param date      The date for which the price needs to be found.
     * @return An {@link Optional} containing the {@link PriceEntity} if an applicable price is found, or {@link Optional#empty()} if not.
     */
    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :date BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    Optional<PriceEntity> findApplicablePriceEntity(@Param("brandId") Integer brandId,
                                                    @Param("productId") Integer productId,
                                                    @Param("date") LocalDateTime date);
}
