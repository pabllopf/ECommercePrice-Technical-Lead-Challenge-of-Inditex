package dev.pabllopf.ecommerceprice.domain.ports.in.authentification;

import dev.pabllopf.ecommerceprice.domain.model.Token;

/**
 * Input port for authentication-related operations.
 * This interface defines a contract for creating authentication tokens.
 */
public interface ICreateToken {

    /**
     * Generates an authentication token based on the provided credentials.
     *
     * @param username The username of the user attempting to authenticate.
     * @param password The password associated with the username.
     * @return A {@link Token} containing the generated authentication token.
     */
    Token createToken(String username, String password);
}
