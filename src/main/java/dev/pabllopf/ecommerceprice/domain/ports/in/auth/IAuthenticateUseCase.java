package dev.pabllopf.ecommerceprice.domain.ports.in.auth;

/**
 * IAuthenticateUseCase defines the contract for the use case of authenticating a user.
 * This interface provides the method for authenticating a user based on their username and password.
 *
 * The implementation of this interface is responsible for handling the user authentication process, which typically involves checking the credentials
 * and generating an authentication token (e.g., a JWT token) if the authentication is successful.
 * This use case is an essential part of any authentication mechanism, ensuring that only authorized users can access certain parts of the system.
 */
public interface IAuthenticateUseCase {

    /**
     * Authenticates a user based on their username and password.
     * This method checks the provided credentials, and if valid, returns an authentication token (e.g., JWT token) for the user.
     * If the credentials are incorrect, an exception is thrown to indicate that authentication failed.
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The password of the user attempting to authenticate.
     * @return A string representing the authentication token (e.g., JWT) for the authenticated user.
     * @throws IllegalArgumentException If the authentication fails (e.g., invalid credentials).
     */
    String authenticate(String username, String password);
}
