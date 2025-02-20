package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.infrastructure.entities.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String SECRET_KEY = "BcKvLsO2FsKAB8KtMcOsOMKJwojDn2TDucOgJsKsWcOzw7jCmUQKw613w5UdAcKGQg==";

    // Dummy users for validation (Replace with real user validation)
    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "password";

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Validate credentials (in real-world use a service to verify against a database)
        if (VALID_USERNAME.equals(loginRequest.getUsername()) && VALID_PASSWORD.equals(loginRequest.getPassword())) {
            // Create JWT token if valid
            long expirationTime = 15 * 60 * 1000; // 15 minutes
            String token = Jwts.builder()
                    .setSubject(loginRequest.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();

            return ResponseEntity.ok(token);
        } else {
            // Return error if invalid credentials
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
        }
    }
}
