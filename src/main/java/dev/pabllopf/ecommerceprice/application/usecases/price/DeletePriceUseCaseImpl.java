package dev.pabllopf.ecommerceprice.application.usecases.price;

import dev.pabllopf.ecommerceprice.domain.ports.in.price.IDeletePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.price.IPriceRepositoryPort;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the use case for deleting a price.
 * This class handles the logic for deleting a price from the repository.
 */
@RequiredArgsConstructor
public class DeletePriceUseCaseImpl implements IDeletePriceUseCase {

    // The repository interface for interacting with the database or storage layer
    private final IPriceRepositoryPort priceRepositoryPort;

    /**
     * Deletes a price entry by its ID.
     * This method delegates the delete operation to the price repository port.
     *
     * @param id The ID of the price entry to be deleted.
     */
    @Override
    public void deletePrice(Long id) {
        // Delete the price entry using the repository by the given ID
        priceRepositoryPort.deleteById(id);
    }
}
