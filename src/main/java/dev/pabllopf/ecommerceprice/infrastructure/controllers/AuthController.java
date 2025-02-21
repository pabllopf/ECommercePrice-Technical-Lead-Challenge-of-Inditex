package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.infrastructure.entities.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    // The secret key used to sign the JWT token.
    private static final String SECRET_KEY = "BcKvLsO2FsKAB8KtMcOsOMKJwojDn2TDucOgJsKsWcOzw7jCmUQKw613w5UdAcKGQg==";

    // Dummy user credentials for validation purposes. Replace with real user validation in a production system.
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "password";

    /**
     * The login endpoint allows users to authenticate and obtain a JWT token.
     * In a real-world scenario, the credentials should be verified against a database or external service.
     *
     * Example:
     * - Username: user
     * - Password: password
     *
     * If the provided credentials are valid, a JWT token is generated and returned to the user.
     * The token is valid for 15 minutes.
     *
     * @param loginRequest The login request containing the username and password provided by the user.
     * @return A ResponseEntity containing the JWT token if authentication is successful, or an error message if the credentials are invalid.
     */
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Validate the provided username and password
        if (VALID_USERNAME.equals(loginRequest.getUsername()) && VALID_PASSWORD.equals(loginRequest.getPassword())) {
            // If credentials are valid, create a JWT token
            long expirationTime = 15 * 60 * 1000; // Set token expiration time to 15 minutes
            String token = Jwts.builder()
                    .setSubject(loginRequest.getUsername()) // Set the username as the subject of the token
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Set expiration date
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign the token using HS256 algorithm and the secret key
                    .compact(); // Generate the JWT token

            // Return the generated token in the response with a 200 OK status
            return ResponseEntity.ok(token);
        } else {
            // If credentials are invalid, return a 403 Forbidden response with an error message
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
        }
    }
}
