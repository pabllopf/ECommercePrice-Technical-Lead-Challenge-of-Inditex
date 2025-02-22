package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.ports.out.auth.ITokenProviderPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The JwtTokenAdapter class is responsible for generating JSON Web Tokens (JWT) for user authentication.
 * It implements the ITokenProviderPort interface and provides the functionality to generate a JWT
 * using a secret key and a set expiration time.
 * This adapter uses the io.jsonwebtoken library to handle the creation and signing of JWT tokens.
 */
@Component
public class JwtTokenAdapter implements ITokenProviderPort {

    /**
     * The secret key used for signing and verifying JWT tokens.
     * This key should be kept secure and not exposed in the codebase.
     */
    private static final String SECRET_KEY = "BcKvLsO2FsKAB8KtMcOsOMKJwojDn2TDucOgJsKsWcOzw7jCmUQKw613w5UdAcKGQg==";

    /**
     * The expiration time for the JWT token, set to 15 minutes.
     * The token will expire after this period and will no longer be valid.
     */
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;

    /**
     * Generates a JWT token for the given username.
     * The token includes the username as the subject, and it will expire after a specified period.
     *
     * @param username The username of the user for whom the token is being generated.
     * @return A JWT token as a string.
     */
    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
