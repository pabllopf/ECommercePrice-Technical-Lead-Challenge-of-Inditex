package dev.pabllopf.ecommerceprice.application.services.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.ICreatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IDeletePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IGetPriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IUpdatePriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class that provides business logic for price-related operations.
 * This class acts as an intermediary between the controller and the use cases,
 * invoking the respective use case implementations for creating, updating, deleting,
 * and retrieving prices.
 */
@Service
@RequiredArgsConstructor
public class PriceService implements ICreatePriceUseCase, IDeletePriceUseCase, IGetPriceUseCase, IUpdatePriceUseCase {

    // Use case interfaces injected to handle price-related operations
    private final ICreatePriceUseCase createPrice;
    private final IGetPriceUseCase getPrice;
    private final IUpdatePriceUseCase updatePrice;
    private final IDeletePriceUseCase deletePrice;

    /**
     * Deletes a price by its ID.
     * This method calls the delete use case to remove a price record from the repository.
     *
     * @param id The ID of the price to delete.
     */
    @Override
    public void deletePrice(Long id) {
        // Delegates the delete operation to the corresponding use case
        deletePrice.deletePrice(id);
    }

    /**
     * Retrieves all prices from the repository.
     * This method calls the get price use case to fetch all prices.
     *
     * @return A list of all prices.
     */
    @Override
    public List<Price> findAllPrices() {
        // Delegates the fetching of all prices to the corresponding use case
        return getPrice.findAllPrices();
    }

    /**
     * Retrieves a specific price by its ID.
     * This method calls the get price use case to fetch the price with the specified ID.
     *
     * @param id The ID of the price to retrieve.
     * @return An Optional containing the found price, or empty if not found.
     */
    @Override
    public Optional<Price> findPriceById(Long id) {
        // Delegates the fetching of a price by ID to the corresponding use case
        return getPrice.findPriceById(id);
    }

    /**
     * Retrieves the applicable price for a product based on the brand ID, product ID, and date.
     * This method calls the get price use case to find the applicable price at the given date and time.
     *
     * @param brandId   The ID of the brand.
     * @param productId The ID of the product.
     * @param date      The date and time to check for the applicable price.
     * @return An Optional containing the applicable price, or empty if no applicable price is found.
     */
    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        // Delegates the fetching of the applicable price to the corresponding use case
        return getPrice.findApplicablePrice(brandId, productId, date);
    }

    /**
     * Updates an existing price in the repository.
     * This method calls the update price use case to update the price details.
     *
     * @param price The price object with updated details.
     */
    @Override
    public void updatePrice(Price price) {
        // Delegates the update operation to the corresponding use case
        updatePrice.updatePrice(price);
    }

    /**
     * Creates a new price in the repository.
     * This method calls the create price use case to save the new price.
     *
     * @param price The price object to be created.
     * @return The created price.
     */
    @Override
    public Price createPrice(Price price) {
        // Delegates the creation of a new price to the corresponding use case
        return createPrice.createPrice(price);
    }
}
