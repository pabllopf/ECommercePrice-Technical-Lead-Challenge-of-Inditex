package dev.pabllopf.ecommerceprice.application.usecases.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.ICreatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the use case for creating a new price.
 * This class handles the logic for saving a new price in the repository.
 */
@RequiredArgsConstructor
public class CreatePriceUseCaseImpl implements ICreatePriceUseCase {

    // The repository interface for interacting with the database or storage layer
    private final IPriceRepositoryPort priceRepositoryPort;

    /**
     * Creates and saves a new price in the repository.
     * This method delegates the save operation to the price repository port.
     *
     * @param price The price object containing the price data to be saved.
     * @return The saved Price object, which includes the generated ID and other details.
     */
    @Override
    public Price createPrice(Price price) {
        // Save the price using the repository and return the result
        return priceRepositoryPort.save(price);
    }
}
