package dev.pabllopf.ecommerceprice.application.usecases.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IUpdatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.price.IPriceRepositoryPort;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the use case for updating a price.
 * This class handles the logic of updating an existing price record in the repository.
 */
@RequiredArgsConstructor
public class UpdatePriceUseCaseImpl implements IUpdatePriceUseCase {

    // The repository interface responsible for interacting with the data storage (e.g., database)
    private final IPriceRepositoryPort priceRepositoryPort;

    /**
     * Updates the details of an existing price.
     * This method sends the updated price information to the repository for storage.
     *
     * @param price The price object containing the updated details.
     */
    @Override
    public void updatePrice(Price price) {
        // Update the price using the repository's update method
        priceRepositoryPort.update(price);
    }
}
