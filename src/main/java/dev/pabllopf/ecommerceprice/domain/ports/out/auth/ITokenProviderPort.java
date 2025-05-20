package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

/**
 * ITokenProviderPort defines the contract for a service that generates tokens
 * based on user authentication. It is used to create tokens (typically JWT tokens)
 * that can be returned to the user upon successful authentication, providing
 * a means to verify the user's identity in subsequent requests.
 * <p>
 * This interface is part of the port-driven architecture, which ensures that the
 * business logic remains decoupled from the actual implementation of token generation.
 * <p>
 * The methods in this interface are expected to be implemented by the infrastructure layer,
 * which could use a variety of methods or libraries for token generation (e.g., JWT, OAuth).
 */
public interface ITokenProviderPort {

    /**
     * Generates a token (such as a JWT) based on the given username.
     * <p>
     * This method is called after a user has been authenticated to generate
     * a token that will be used for identifying and authorizing the user in future requests.
     * <p>
     * The token may include information like the user's identity (e.g., username) and
     * other claims or metadata related to the user, depending on the implementation.
     *
     * @param username The username of the user for whom the token is being generated.
     * @return A token (typically a JWT or similar) that represents the authenticated user.
     */
    String generateToken(String username);
}
