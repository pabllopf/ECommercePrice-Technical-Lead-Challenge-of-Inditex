package dev.pabllopf.ecommerceprice.infrastructure.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

    @Test
    void testGettersAndSetters() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        assertEquals("testUser", loginRequest.getUsername());
        assertEquals("testPass", loginRequest.getPassword());
    }

    @Test
    void testRequiredArgsConstructor() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");

        assertEquals("testUser", loginRequest.getUsername());
        assertEquals("testPass", loginRequest.getPassword());
    }
}