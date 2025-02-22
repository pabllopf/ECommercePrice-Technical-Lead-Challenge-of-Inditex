package dev.pabllopf.ecommerceprice.application.usecases.auth;

import dev.pabllopf.ecommerceprice.domain.ports.in.auth.IAuthenticateUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IAuthenticatePort;
import lombok.RequiredArgsConstructor;

/**
 * AuthenticateUseCaseImpl handles the business logic for authenticating a user.
 * It implements the IAuthenticateUseCase interface and delegates the authentication process
 * to the appropriate port, IAuthenticatePort.
 *
 * This class acts as the use case layer for user authentication, ensuring that the logic
 * for user authentication is separated from the infrastructure layer.
 */
@RequiredArgsConstructor
public class AuthenticateUseCaseImpl implements IAuthenticateUseCase {

    /**
     * The port that handles the actual authentication logic.
     * It delegates the work to the infrastructure layer, where user verification and
     * token generation are performed.
     */
    private final IAuthenticatePort authenticatePort;

    /**
     * Authenticates a user by verifying their username and password.
     * Delegates the authentication process to the authenticatePort.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return A JWT token if authentication is successful, or throws an exception if authentication fails.
     */
    @Override
    public String authenticate(String username, String password) {
        return authenticatePort.authenticate(username, password);
    }
}
