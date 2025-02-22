package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

/**
 * ICreatePriceUseCase defines the contract for the use case of creating a new price record.
 * This interface is part of the domain layer and encapsulates the logic required to create a new price entry.
 * It serves as the entry point for the application layer to interact with the core business logic related to price creation.
 *
 * This interface ensures that the logic for creating a price is decoupled from the specific infrastructure
 * or database-related operations, making the business logic easier to test and maintain.
 */
public interface ICreatePriceUseCase {

    /**
     * Creates a new price record.
     * This method accepts a Price object and processes it to create a new entry in the system.
     * The actual implementation of this method will typically involve saving the price data to a database.
     *
     * @param price The price object containing the details of the price to be created.
     * @return The created Price object with the corresponding ID and other details (after being persisted).
     */
    Price createPrice(Price price);
}
