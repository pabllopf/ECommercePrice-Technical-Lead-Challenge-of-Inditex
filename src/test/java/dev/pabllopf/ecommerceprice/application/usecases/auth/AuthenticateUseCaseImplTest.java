package dev.pabllopf.ecommerceprice.application.usecases.auth;

import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IAuthenticatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AuthenticateUseCaseImplTest {

    @Mock
    private IAuthenticatePort authenticatePort;

    @InjectMocks
    private AuthenticateUseCaseImpl authenticateUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticateSuccess() {
        String username = "testUser";
        String password = "testPassword";
        String expectedToken = "token";
        when(authenticatePort.authenticate(username, password)).thenReturn(expectedToken);

        String actualToken = authenticateUseCase.authenticate(username, password);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testAuthenticateFailure() {
        String username = "testUser";
        String password = "wrongPassword";

        when(authenticatePort.authenticate(username, password)).thenThrow(new IllegalArgumentException("Invalid credentials"));

        assertThrows(IllegalArgumentException.class, () -> authenticateUseCase.authenticate(username, password));
    }
}