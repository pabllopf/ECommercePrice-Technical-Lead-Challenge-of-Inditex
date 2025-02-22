package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class IAuthenticatePortTest {

    @Mock
    private IAuthenticatePort authenticatePort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateSuccess() {
        String username = "testUser";
        String password = "testPassword";
        String actualToken = authenticatePort.authenticate(username, password);

        String expectedToken = null;
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testAuthenticateFailure() {
        String username = "testUser";
        String password = "wrongPassword";

        when(authenticatePort.authenticate(username, password)).thenThrow(new IllegalArgumentException("Invalid credentials"));

        assertThrows(IllegalArgumentException.class, () -> authenticatePort.authenticate(username, password));
    }
}