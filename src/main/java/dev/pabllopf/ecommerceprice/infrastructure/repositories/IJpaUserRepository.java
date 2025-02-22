package dev.pabllopf.ecommerceprice.infrastructure.repositories;

import dev.pabllopf.ecommerceprice.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * IJpaUserRepository is a Spring Data JPA repository interface for performing CRUD operations on UserEntity objects.
 * It extends JpaRepository, providing default methods for interacting with the "users" table in the database.
 * The interface also includes custom query methods specific to user-related operations.
 */
public interface IJpaUserRepository extends JpaRepository<UserEntity, String> {

    /**
     * Finds a user by their username.
     * This method is used to retrieve a user record based on the provided username.
     * The username is expected to be unique.
     *
     * @param username The username of the user to find.
     * @return An Optional containing the found UserEntity, or an empty Optional if no user is found with the provided username.
     */
    Optional<UserEntity> findByUsername(String username);
}
