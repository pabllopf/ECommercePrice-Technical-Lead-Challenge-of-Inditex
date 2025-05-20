package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.User;
import dev.pabllopf.ecommerceprice.infrastructure.entities.UserEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JpaUserRepositoryAdapterTest {

    @Mock
    private IJpaUserRepository jpaUserRepository;

    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByUsername_UserExists() {
        String username = "testuser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);

        when(jpaUserRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        Optional<User> user = jpaUserRepositoryAdapter.findByUsername(username);

        assertTrue(user.isPresent());
        assertEquals(username, user.get().getUsername());
    }

    @Test
    void testFindByUsername_UserDoesNotExist() {
        String username = "nonexistentuser";
        when(jpaUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        Optional<User> user = jpaUserRepositoryAdapter.findByUsername(username);

        assertFalse(user.isPresent());
    }
}