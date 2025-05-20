package dev.pabllopf.ecommerceprice.domain.ports.out.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IPriceRepositoryPort defines the contract for the repository layer responsible for handling price-related data.
 * It provides methods for saving, retrieving, updating, and deleting price records.
 * This interface abstracts the interaction with the underlying data store (e.g., a database), allowing the rest of the application to work with the price data without worrying about how it's persisted.
 */
public interface IPriceRepositoryPort {

    /**
     * Saves a new price record in the repository.
     * This method is responsible for persisting a new price object in the repository.
     *
     * @param price The price object to be saved.
     * @return The saved price object with its generated ID.
     */
    Price save(Price price);

    /**
     * Finds the applicable price for a given brand, product, and date.
     * This method checks the repository to find the applicable price based on the brand, product, and date.
     * It returns an optional price, as there might not be a matching price for the given parameters.
     *
     * @param brandId   The ID of the brand for which the price is being requested.
     * @param productId The ID of the product for which the price is being requested.
     * @param date      The date when the price is to be applied.
     * @return An optional containing the applicable price if found, or an empty optional if no applicable price is found.
     */
    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);

    /**
     * Finds a price by its ID.
     * This method retrieves a price from the repository based on its unique ID.
     *
     * @param id The ID of the price record to be retrieved.
     * @return An optional containing the price with the corresponding ID, or an empty optional if no price is found.
     */
    Optional<Price> findById(Long id);

    /**
     * Retrieves all price records from the repository.
     * This method fetches all the price records stored in the repository.
     *
     * @return A list of all price records.
     */
    List<Price> findAll();

    /**
     * Updates an existing price record in the repository.
     * This method allows updating an existing price record. It returns an optional price after the update,
     * which may or may not be present if the update was successful.
     *
     * @param price The price object with updated data to be persisted.
     * @return An optional containing the updated price, or an empty optional if the update fails.
     */
    Optional<Price> update(Price price);

    /**
     * Deletes a price record by its ID.
     * This method deletes the price record with the specified ID from the repository.
     *
     * @param id The ID of the price record to be deleted.
     * @return True if the deletion was successful, or false if the record with the specified ID could not be found.
     */
    boolean deleteById(Long id);
}
