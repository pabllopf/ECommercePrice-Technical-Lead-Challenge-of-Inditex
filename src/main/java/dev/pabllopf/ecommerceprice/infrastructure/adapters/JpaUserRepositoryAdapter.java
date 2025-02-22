package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.User;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IUserRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.entities.UserEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * The JpaUserRepositoryAdapter class is an implementation of the IUserRepositoryPort interface.
 * It acts as an adapter between the domain layer and the data layer for user-related operations.
 * This class provides the ability to retrieve user information from the underlying database
 * using the JPA repository and map the data to the domain model (User).
 */
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements IUserRepositoryPort {

    /**
     * The JPA repository for user entities, used to interact with the underlying database.
     */
    private final IJpaUserRepository jpaUserRepository;

    /**
     * Finds a user by their username. This method uses the underlying JPA repository to fetch the user
     * from the database and maps the result to the domain model (User).
     *
     * @param username The username of the user to be retrieved.
     * @return An Optional containing the mapped User if found, or an empty Optional if no user is found.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserEntity::toDomain);
    }
}
