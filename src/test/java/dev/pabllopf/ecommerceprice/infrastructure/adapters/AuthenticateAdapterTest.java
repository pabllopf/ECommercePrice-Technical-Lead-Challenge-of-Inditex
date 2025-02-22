package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.User;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.ITokenProviderPort;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IUserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticateAdapterTest {

    @Mock
    private IUserRepositoryPort userRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ITokenProviderPort tokenProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateSuccess() {
        String username = "testuser";
        String password = "password";
        String encodedPassword = "encodedPassword";
        User user = new User(1L, username, encodedPassword, null);

        when(userRepositoryPort.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(tokenProvider.generateToken(username)).thenReturn("token");

        AuthenticateAdapter authenticateAdapter = new AuthenticateAdapter(userRepositoryPort, passwordEncoder, tokenProvider);
        String token = authenticateAdapter.authenticate(username, password);

        assertNotNull(token);
        assertEquals("token", token);
    }

    @Test
    void testAuthenticateInvalidUsername() {
        String username = "invaliduser";
        String password = "password";

        when(userRepositoryPort.findByUsername(username)).thenReturn(Optional.empty());

        AuthenticateAdapter authenticateAdapter = new AuthenticateAdapter(userRepositoryPort, passwordEncoder, tokenProvider);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authenticateAdapter.authenticate(username, password);
        });

        assertEquals("Invalid username or password", exception.getMessage());
    }

    @Test
    void testAuthenticateInvalidPassword() {
        String username = "testuser";
        String password = "invalidpassword";
        String encodedPassword = "encodedPassword";
        User user = new User(1L, username, encodedPassword, null);

        when(userRepositoryPort.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        AuthenticateAdapter authenticateAdapter = new AuthenticateAdapter(userRepositoryPort, passwordEncoder, tokenProvider);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            authenticateAdapter.authenticate(username, password);
        });

        assertEquals("Invalid username or password", exception.getMessage());
    }
}