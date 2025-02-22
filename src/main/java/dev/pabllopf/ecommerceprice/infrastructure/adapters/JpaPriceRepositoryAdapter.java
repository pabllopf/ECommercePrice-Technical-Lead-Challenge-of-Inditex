package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.price.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The JpaPriceRepositoryAdapter class implements the IPriceRepositoryPort interface,
 * serving as an adapter between the domain model and the persistence layer for price-related operations.
 * It uses the JPA repository to manage price records in the database and maps them to the domain model.
 */
@RequiredArgsConstructor
public class JpaPriceRepositoryAdapter implements IPriceRepositoryPort {

    /**
     * The JPA repository used for interacting with the database.
     * This repository performs CRUD operations on price entities.
     */
    private final IJpaPriceRepository jpaPriceRepository;

    /**
     * Saves a price record in the database. This method converts the domain model Price to a PriceEntity,
     * saves it to the database, and converts it back to the domain model Price.
     *
     * @param price The price object to be saved in the database.
     * @return The saved price object in domain model format.
     */
    @Override
    public Price save(Price price) {
        return jpaPriceRepository.save(PriceEntity.fromDomain(price)).toDomain();
    }

    /**
     * Finds the applicable price for a given brand, product, and date.
     * It fetches the PriceEntity that matches the provided criteria,
     * and maps it to the domain model Price.
     *
     * @param brandId The ID of the brand to which the price is associated.
     * @param productId The ID of the product for which the price is applicable.
     * @param date The date when the price is applicable.
     * @return An Optional containing the applicable price, or an empty Optional if no price is found.
     */
    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return jpaPriceRepository.findApplicablePriceEntity(brandId, productId, date).map(PriceEntity::toDomain);
    }

    /**
     * Finds a price by its ID. This method retrieves the PriceEntity with the specified ID
     * and maps it to the domain model Price.
     *
     * @param id The ID of the price to be retrieved.
     * @return An Optional containing the price if found, or an empty Optional if no price is found.
     */
    @Override
    public Optional<Price> findById(Long id) {
        return jpaPriceRepository.findById(id).map(PriceEntity::toDomain);
    }

    /**
     * Retrieves all price records from the database.
     * Each PriceEntity is converted into a domain model Price and returned as a list.
     *
     * @return A list of all prices in the database.
     */
    @Override
    public List<Price> findAll() {
        return jpaPriceRepository.findAll().stream().map(PriceEntity::toDomain).toList();
    }

    /**
     * Updates an existing price record. First, the method checks if the price exists by its ID,
     * and if found, updates the record. The updated PriceEntity is converted back to the domain model Price.
     *
     * @param price The price object containing updated data.
     * @return An Optional containing the updated price, or an empty Optional if the price is not found.
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
     * If the price is not found, the method returns false.
     *
     * @param id The ID of the price to be deleted.
     * @return True if the price was deleted, false if the price was not found.
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
