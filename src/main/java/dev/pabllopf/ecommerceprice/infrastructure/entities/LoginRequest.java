package dev.pabllopf.ecommerceprice.infrastructure.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The LoginRequest class is used to represent the data structure for user authentication.
 * It contains the user's username and password, which are provided when attempting to log in.
 * This class is typically used in the login API request to pass the credentials to the authentication service.
 * This class is annotated with Lombok annotations to automatically generate getter, setter, and constructor methods.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequest {

    /**
     * The username provided by the user for authentication.
     * It is used to identify the user in the system.
     */
    private String username;

    /**
     * The password provided by the user for authentication.
     * It is used along with the username to validate the user's identity.
     */
    private String password;
}
