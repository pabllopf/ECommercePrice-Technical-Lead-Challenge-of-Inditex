package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ITokenProviderPortTest {

    @Mock
    private ITokenProviderPort tokenProviderPort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String expectedToken = "mocked-jwt-token";

        when(tokenProviderPort.generateToken(username)).thenReturn(expectedToken);

        String actualToken = tokenProviderPort.generateToken(username);
        assertNotNull(actualToken);
        assertEquals(expectedToken, actualToken);
    }
}