package dev.pabllopf.ecommerceprice.application.services.auth;

import dev.pabllopf.ecommerceprice.domain.ports.in.auth.IAuthenticateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AuthenticateServiceTest {

    @Mock
    private IAuthenticateUseCase authenticateUseCase;

    @InjectMocks
    private AuthenticateService authenticateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticateSuccess() {
        String username = "user1";
        String password = "user1";
        String expectedToken = "token";
        when(authenticateUseCase.authenticate(username, password)).thenReturn(expectedToken);

        String actualToken = authenticateService.authenticate(username, password);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testAuthenticateFailure() {
        String username = "testUser";
        String password = "wrongPassword";

        when(authenticateUseCase.authenticate(username, password)).thenThrow(new IllegalArgumentException("Invalid credentials"));

        assertThrows(IllegalArgumentException.class, () -> authenticateService.authenticate(username, password));
    }
}