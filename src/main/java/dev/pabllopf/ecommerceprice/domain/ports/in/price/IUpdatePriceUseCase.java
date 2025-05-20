package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

/**
 * IUpdatePriceUseCase defines the contract for the use case of updating price-related data.
 * This interface provides the method for updating an existing price record in the system.
 *
 * By implementing this interface, the system can expose the business logic for updating price records in a decoupled and maintainable way.
 * It abstracts the details of how a price update is performed, allowing for easier testing and separation of concerns between layers.
 */
public interface IUpdatePriceUseCase {

    /**
     * Updates the details of an existing price record in the system.
     * This method takes a Price object containing the updated price data and applies the changes to the relevant price record in the database.
     *
     * @param price The Price object containing the updated price data to be applied.
     */
    void updatePrice(Price price);
}
