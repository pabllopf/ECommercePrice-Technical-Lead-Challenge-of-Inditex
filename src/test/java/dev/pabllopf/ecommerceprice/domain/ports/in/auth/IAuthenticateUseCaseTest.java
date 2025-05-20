package dev.pabllopf.ecommerceprice.domain.ports.in.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class IAuthenticateUseCaseTest {

    @Mock
    private IAuthenticateUseCase authenticateUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateSuccess() {
        String username = "testUser";
        String password = "testPassword";
        String expectedToken = "token";
        when(authenticateUseCase.authenticate(username, password)).thenReturn(expectedToken);

        String actualToken = authenticateUseCase.authenticate(username, password);

        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testAuthenticateFailure() {
        String username = "testUser";
        String password = "wrongPassword";

        when(authenticateUseCase.authenticate(username, password)).thenThrow(new IllegalArgumentException("Invalid credentials"));

        assertThrows(IllegalArgumentException.class, () -> authenticateUseCase.authenticate(username, password));
    }
}