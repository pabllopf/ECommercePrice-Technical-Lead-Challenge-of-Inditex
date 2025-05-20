package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

/**
 * IAuthenticatePort defines the contract for authentication functionality.
 * This interface is used to authenticate a user based on their username and password.
 * It is implemented in the infrastructure layer to integrate with specific authentication mechanisms.
 * <p>
 * The authentication process typically involves verifying the user's credentials (username and password)
 * and returning a token (such as a JWT) that can be used to authorize the user in future requests.
 * <p>
 * This interface ensures that the core business logic is decoupled from the specific authentication
 * implementations, adhering to the principles of a port-driven architecture (also known as hexagonal architecture).
 */
public interface IAuthenticatePort {

    /**
     * Authenticates a user based on their username and password.
     * This method is responsible for validating the provided credentials.
     * If the credentials are valid, a token (typically a JWT) is generated and returned.
     * <p>
     * The token returned can be used in future requests to identify and authorize the user.
     *
     * @param username The username of the user trying to authenticate.
     * @param password The password of the user trying to authenticate.
     * @return A token (typically JWT) that represents the authenticated user.
     * @throws IllegalArgumentException If the username or password is invalid.
     */
    String authenticate(String username, String password);
}
