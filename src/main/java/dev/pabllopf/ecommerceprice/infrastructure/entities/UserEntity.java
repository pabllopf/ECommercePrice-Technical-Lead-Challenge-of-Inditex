package dev.pabllopf.ecommerceprice.infrastructure.entities;

import dev.pabllopf.ecommerceprice.domain.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserEntity represents the database entity for user-related data.
 * It is mapped to the "users" table and contains all the necessary fields for storing user information.
 * The class provides conversion methods between the entity and the domain model for easier integration in the service layer.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    /**
     * The unique identifier for the user record.
     * This field is automatically generated and is used to reference the user entity in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * This field is used to uniquely identify the user.
     */
    private String username;

    /**
     * The password of the user.
     * This field stores the user's password (typically encrypted).
     */
    private String password;

    /**
     * The role of the user.
     * This defines the user's role (e.g., ADMIN, USER) within the system.
     */
    private String role;

    /**
     * Converts a domain model (User) to a UserEntity for persistence in the database.
     *
     * @param user The domain model to be converted.
     * @return A UserEntity representing the provided domain model.
     */
    public static UserEntity fromDomain(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        return entity;
    }

    /**
     * Converts this entity to its corresponding domain model.
     * The domain model is used in the application logic and service layer.
     *
     * @return A User object representing the entity data.
     */
    public User toDomain() {
        return new User(id, username, password, role);
    }
}
