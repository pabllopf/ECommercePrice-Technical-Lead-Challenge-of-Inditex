package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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