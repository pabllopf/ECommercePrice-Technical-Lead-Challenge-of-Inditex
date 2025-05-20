package dev.pabllopf.ecommerceprice.domain.ports.in.price;

/**
 * IDeletePriceUseCase defines the contract for the use case of deleting an existing price record.
 * This interface serves as the entry point for the application layer to interact with the core business logic related to price deletion.
 * It is part of the domain layer and abstracts the details of how a price record is deleted from the system.
 * <p>
 * By following this approach, the business logic for deleting prices is decoupled from infrastructure or persistence logic,
 * ensuring better testability and maintainability.
 */
public interface IDeletePriceUseCase {

    /**
     * Deletes a price record by its ID.
     * This method processes the deletion of a price record identified by the provided ID.
     * The actual implementation typically involves interacting with the repository layer to remove the record from the database.
     *
     * @param id The ID of the price to be deleted.
     * @throws IllegalArgumentException if the price with the given ID does not exist.
     */
    void deletePrice(Long id);
}
