package dev.pabllopf.ecommerceprice.domain.ports.out;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IPriceRepositoryPort {

    /**
     * Saves a new price record in the repository.
     *
     * @param price The price object to be saved.
     * @return The saved price object.
     */
    Price save(Price price);

    /**
     * Finds the applicable price for a given brand, product, and date.
     * This method returns an optional price, as there might not be a matching price for the given parameters.
     *
     * @param brandId The ID of the brand.
     * @param productId The ID of the product.
     * @param date The date on which the price is to be applied.
     * @return An optional price, which may or may not be present.
     */
    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);

    /**
     * Finds a price by its ID.
     *
     * @param id The ID of the price to be retrieved.
     * @return An optional price with the corresponding ID, or an empty optional if no price is found.
     */
    Optional<Price> findById(Long id);

    /**
     * Retrieves all price records from the repository.
     *
     * @return A list of all price records.
     */
    List<Price> findAll();

    /**
     * Updates an existing price record in the repository.
     *
     * @param price The price object with updated data.
     * @return An optional price after update, which may or may not be present.
     */
    Optional<Price> update(Price price);

    /**
     * Deletes a price record by its ID.
     *
     * @param id The ID of the price record to be deleted.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean deleteById(Long id);
}
