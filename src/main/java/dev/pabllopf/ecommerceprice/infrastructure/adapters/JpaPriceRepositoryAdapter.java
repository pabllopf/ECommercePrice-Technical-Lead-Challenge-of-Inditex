package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaPriceRepositoryAdapter implements IPriceRepositoryPort {

    // The JPA repository used for interacting with the database
    private final IJpaPriceRepository jpaPriceRepository;

    /**
     * Saves a price record in the database.
     * Converts the domain model Price to the entity PriceEntity, saves it, and then converts it back to the domain model.
     *
     * @param price The price object to be saved.
     * @return The saved price object from the database.
     */
    @Override
    public Price save(Price price) {
        return jpaPriceRepository.save(PriceEntity.fromDomain(price)).toDomain();
    }

    /**
     * Finds the applicable price for a given brand, product, and date.
     * Converts the found PriceEntity to the domain model Price.
     *
     * @param brandId The ID of the brand.
     * @param productId The ID of the product.
     * @param date The date when the price is applicable.
     * @return An Optional containing the applicable price, or an empty Optional if no price is found.
     */
    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return jpaPriceRepository.findApplicablePriceEntity(brandId, productId, date).map(PriceEntity::toDomain);
    }

    /**
     * Finds a price by its ID.
     * Converts the found PriceEntity to the domain model Price.
     *
     * @param id The ID of the price to be retrieved.
     * @return An Optional containing the price, or an empty Optional if no price is found.
     */
    @Override
    public Optional<Price> findById(Long id) {
        return jpaPriceRepository.findById(id).map(PriceEntity::toDomain);
    }

    /**
     * Retrieves all price records from the database.
     * Converts each PriceEntity to the domain model Price and returns them in a list.
     *
     * @return A list of all prices.
     */
    @Override
    public List<Price> findAll() {
        return jpaPriceRepository.findAll().stream().map(PriceEntity::toDomain).toList();
    }

    /**
     * Updates an existing price record.
     * First, it checks if the price exists by its ID, and if so, updates it.
     * Converts the updated PriceEntity back to the domain model Price.
     *
     * @param price The price object with updated data.
     * @return An Optional containing the updated price, or an empty Optional if the price was not found.
     */
    @Override
    public Optional<Price> update(Price price) {
        return jpaPriceRepository.findById(price.getId())
                .map(priceEntity -> jpaPriceRepository
                        .save(PriceEntity.fromDomain(price))
                        .toDomain());
    }

    /**
     * Deletes a price record by its ID.
     * If the price is found, it is deleted, and the method returns true.
     * Otherwise, it returns false.
     *
     * @param id The ID of the price to be deleted.
     * @return True if the price was deleted, false if it was not found.
     */
    @Override
    public boolean deleteById(Long id) {
        return jpaPriceRepository.findById(id)
                .map(priceEntity -> {
                    jpaPriceRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}
