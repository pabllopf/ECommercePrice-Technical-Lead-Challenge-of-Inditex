package dev.pabllopf.ecommerceprice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * User represents a user in the system.
 * It contains the user's unique identifier, username, password, and role.
 * This class serves as the domain model for user-related operations.
 */
@Getter
@AllArgsConstructor
public class User {

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The username associated with the user account.
     */
    private String username;

    /**
     * The password of the user (usually stored in a hashed format).
     */
    private String password;

    /**
     * The role of the user, which determines the level of access within the system (e.g., "admin", "user").
     */
    private String role;
}
