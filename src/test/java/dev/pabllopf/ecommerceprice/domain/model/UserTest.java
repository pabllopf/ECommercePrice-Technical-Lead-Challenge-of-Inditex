package dev.pabllopf.ecommerceprice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        Long id = 1L;
        String username = "testuser";
        String password = "password123";
        String role = "admin";

        User user = new User(id, username, password, role);

        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }
}