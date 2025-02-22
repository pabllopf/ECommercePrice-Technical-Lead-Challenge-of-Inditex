package dev.pabllopf.ecommerceprice.application.services.auth;

import dev.pabllopf.ecommerceprice.domain.ports.in.auth.IAuthenticateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * AuthenticateService is the service class that acts as an entry point for user authentication.
 * It implements the IAuthenticateUseCase interface and delegates the authentication task
 * to the underlying use case logic.
 *
 * This class provides an abstraction layer between the controller and the use case, ensuring that
 * the authentication logic is separated from the web layer.
 */
@Service
@RequiredArgsConstructor
public class AuthenticateService implements IAuthenticateUseCase {

    /**
     * The use case responsible for handling the authentication logic.
     * This is injected into the service class via the constructor.
     */
    private final IAuthenticateUseCase authenticateUseCase;

    /**
     * Authenticates a user by delegating the authentication process to the use case layer.
     * The service class is designed to call the underlying use case, which contains the
     * business logic for authenticating the user and generating a JWT token.
     *
     * @param username The username of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return A JWT token if authentication is successful, or throws an exception if authentication fails.
     */
    @Override
    public String authenticate(String username, String password) {
        return authenticateUseCase.authenticate(username, password);
    }
}
