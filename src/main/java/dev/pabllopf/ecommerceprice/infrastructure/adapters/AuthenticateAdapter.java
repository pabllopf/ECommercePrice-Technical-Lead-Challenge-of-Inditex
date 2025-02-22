package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.User;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IAuthenticatePort;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.ITokenProviderPort;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * The AuthenticateAdapter class implements the IAuthenticatePort interface and provides the
 * authentication logic for the application. It interacts with the user repository to verify user credentials
 * and uses a password encoder to compare the provided password with the stored password.
 * Upon successful authentication, it generates a JWT token using the token provider.
 */
@RequiredArgsConstructor
public class AuthenticateAdapter implements IAuthenticatePort {

    /**
     * The user repository port used for finding users by their username.
     */
    private final IUserRepositoryPort userRepositoryPort;

    /**
     * The password encoder used for verifying the user's password.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The token provider used for generating JWT tokens.
     */
    private final ITokenProviderPort tokenProvider;

    /**
     * Authenticates a user by validating their username and password.
     * If the credentials are valid, a JWT token is generated and returned.
     * If invalid, an exception is thrown.
     *
     * @param username The username of the user trying to authenticate.
     * @param password The password provided by the user.
     * @return A JWT token string if the authentication is successful.
     * @throws IllegalArgumentException If the username or password is invalid.
     */
    @Override
    public String authenticate(String username, String password) {
        Optional<User> userOptional = userRepositoryPort.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return tokenProvider.generateToken(username);
    }
}
