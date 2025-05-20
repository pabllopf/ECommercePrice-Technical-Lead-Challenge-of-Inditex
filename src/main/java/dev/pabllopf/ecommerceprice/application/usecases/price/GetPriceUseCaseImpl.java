package dev.pabllopf.ecommerceprice.application.usecases.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IGetPriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.price.IPriceRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the use case for retrieving price information.
 * This class handles the logic for finding prices based on various criteria,
 * such as by ID or applicable price for a given product and brand at a specific date.
 */
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements IGetPriceUseCase {

    // The repository interface for interacting with the database or storage layer
    private final IPriceRepositoryPort priceRepositoryPort;

    /**
     * Retrieves all prices from the repository.
     * This method returns a list of all available price records.
     *
     * @return A list of all prices.
     */
    @Override
    public List<Price> findAllPrices() {
        // Fetch all prices using the repository
        return priceRepositoryPort.findAll();
    }

    /**
     * Retrieves a price by its ID from the repository.
     * This method returns an Optional containing the price if found, or empty if not found.
     *
     * @param id The ID of the price to retrieve.
     * @return An Optional containing the found price or empty if not found.
     */
    @Override
    public Optional<Price> findPriceById(Long id) {
        // Fetch price by ID using the repository
        return priceRepositoryPort.findById(id);
    }

    /**
     * Retrieves the applicable price for a given brand and product at a specific date and time.
     * This method checks the repository for the most relevant price for the specified criteria.
     *
     * @param brandId   The ID of the brand.
     * @param productId The ID of the product.
     * @param date      The date and time when the price is applicable.
     * @return An Optional containing the applicable price, or empty if no applicable price is found.
     */
    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        // Fetch applicable price using the repository based on the given parameters
        return priceRepositoryPort.findApplicablePrice(brandId, productId, date);
    }
}
