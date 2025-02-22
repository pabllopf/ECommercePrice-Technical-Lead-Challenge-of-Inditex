package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IGetPriceUseCase defines the contract for the use case of retrieving price-related data.
 * This interface provides methods for accessing prices in the system, allowing for various operations such as
 * fetching all prices, finding a price by ID, and retrieving the applicable price for a specific product and brand at a given time.
 *
 * By implementing this interface, the system can expose business logic for retrieving prices in a decoupled and maintainable way.
 * It abstracts the details of how prices are retrieved, allowing for easier testing and separation of concerns between layers.
 */
public interface IGetPriceUseCase {

    /**
     * Retrieves all price records in the system.
     * This method returns a list of all available prices. It is typically used to fetch a full list of price entries stored in the system.
     *
     * @return A list of all Price objects available in the system.
     */
    List<Price> findAllPrices();

    /**
     * Retrieves a price record by its unique ID.
     * This method returns an Optional of Price. If the price exists, it is returned; otherwise, an empty Optional is returned.
     * It is typically used to fetch a single price based on its ID.
     *
     * @param id The unique identifier of the price to be retrieved.
     * @return An Optional containing the Price if found, or an empty Optional if not found.
     */
    Optional<Price> findPriceById(Long id);

    /**
     * Retrieves the applicable price for a specific brand, product, and date.
     * This method returns an Optional of Price. If an applicable price is found for the given brand, product, and date, it is returned.
     * If no applicable price is found, an empty Optional is returned.
     *
     * @param brandId The ID of the brand for which the price is being retrieved.
     * @param productId The ID of the product for which the price is being retrieved.
     * @param date The date when the price is applicable.
     * @return An Optional containing the applicable Price if found, or an empty Optional if no applicable price is found.
     */
    Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date);
}
