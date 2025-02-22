package dev.pabllopf.ecommerceprice.infrastructure.entities;

import dev.pabllopf.ecommerceprice.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {

    @Test
    void testGettersAndSetters() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPass");
        userEntity.setRole("USER");

        assertEquals(1L, userEntity.getId());
        assertEquals("testUser", userEntity.getUsername());
        assertEquals("testPass", userEntity.getPassword());
        assertEquals("USER", userEntity.getRole());
    }

    @Test
    void testToDomain() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPass");
        userEntity.setRole("USER");

        User user = userEntity.toDomain();

        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPass", user.getPassword());
        assertEquals("USER", user.getRole());
    }

    @Test
    void testFromDomain() {
        User user = new User(1L, "testUser", "testPass", "USER");

        UserEntity userEntity = UserEntity.fromDomain(user);

        assertEquals(1L, userEntity.getId());
        assertEquals("testUser", userEntity.getUsername());
        assertEquals("testPass", userEntity.getPassword());
        assertEquals("USER", userEntity.getRole());
    }
}