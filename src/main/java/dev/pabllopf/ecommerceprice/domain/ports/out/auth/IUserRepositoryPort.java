package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

import dev.pabllopf.ecommerceprice.domain.model.User;

import java.util.Optional;

/**
 * IUserRepositoryPort defines the contract for interacting with the user repository
 * in the infrastructure layer. It is used to fetch user-related data, specifically
 * to find a user by their username. This interface is part of the port-driven architecture,
 * ensuring that the application's business logic can remain decoupled from the persistence details.
 * <p>
 * The methods in this interface are expected to be implemented by the infrastructure layer,
 * which may interact with databases or other data sources.
 */
public interface IUserRepositoryPort {

    /**
     * Finds a user by their username.
     * <p>
     * This method will query the underlying data store (e.g., a database) for a user
     * with the given username. If the user exists, the method returns an Optional
     * containing the User object. If no user is found with the provided username,
     * it returns an empty Optional.
     *
     * @param username The username of the user to search for.
     * @return An Optional containing the User object if found, or an empty Optional if not.
     */
    Optional<User> findByUsername(String username);
}
