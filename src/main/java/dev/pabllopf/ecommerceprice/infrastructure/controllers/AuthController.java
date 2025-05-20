package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.application.services.auth.AuthenticateService;
import dev.pabllopf.ecommerceprice.infrastructure.entities.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The AuthController class handles HTTP requests related to user authentication.
 * It provides an endpoint for user login, where the system authenticates the user
 * based on the provided credentials and returns a JWT token upon successful authentication.
 */
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * The AuthenticateService is used to perform authentication logic.
     */
    private final AuthenticateService authenticateUserUseCase;

    /**
     * The login method is responsible for authenticating the user.
     * It receives a LoginRequest containing the username and password,
     * validates the credentials, and returns a JWT token if authentication is successful.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A ResponseEntity containing the JWT token if the login is successful.
     * @throws IllegalArgumentException If the username or password is invalid.
     */
    @PostMapping("login")
    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns a JWT token",
            requestBody = @RequestBody(
                    description = "User credentials for authentication",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "\"username\": \"user1\",\n" +
                                    "\"password\": \"user1\"\n" +
                                    "}")
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "403", description = "Invalid username or password")
    })
    public ResponseEntity<String> login(@org.springframework.web.bind.annotation.RequestBody LoginRequest loginRequest){
        // The username and password from the login request are passed to the authenticate service
        String token = authenticateUserUseCase.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        // If authentication is successful, the JWT token is returned as part of the response
        return ResponseEntity.ok(token);
    }
}
