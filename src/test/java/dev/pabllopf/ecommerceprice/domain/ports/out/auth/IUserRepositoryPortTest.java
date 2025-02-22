package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

import dev.pabllopf.ecommerceprice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class IUserRepositoryPortTest {

    @Mock
    private IUserRepositoryPort userRepositoryPort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByUsernameSuccess() {
        User expectedUser = new User(1L, "testUser", "testPass", "USER");
        String username = "testUser";
        when(userRepositoryPort.findByUsername(username)).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = userRepositoryPort.findByUsername(username);
        assertTrue(actualUser.isPresent());
        assertEquals(expectedUser, actualUser.get());
    }

    @Test
    public void testFindByUsernameNotFound() {
        String username = "nonExistentUser";

        when(userRepositoryPort.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> actualUser = userRepositoryPort.findByUsername(username);
        assertTrue(actualUser.isEmpty());
    }
}