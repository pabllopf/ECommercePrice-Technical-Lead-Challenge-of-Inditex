package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.ports.out.auth.ITokenProviderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTokenAdapterTest {

    @InjectMocks
    private JwtTokenAdapter jwtTokenAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String token = jwtTokenAdapter.generateToken(username);

        assertNotNull(token);
        assertFalse(token.contains(username));
    }
}