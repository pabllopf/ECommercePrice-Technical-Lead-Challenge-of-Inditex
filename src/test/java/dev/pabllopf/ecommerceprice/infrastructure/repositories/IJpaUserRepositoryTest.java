package dev.pabllopf.ecommerceprice.infrastructure.repositories;

import dev.pabllopf.ecommerceprice.infrastructure.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IJpaUserRepositoryTest {

    @Autowired
    private IJpaUserRepository jpaUserRepository;

 @Test
 void testFindByUsername() {
     // Given
     String username = "user5";
     UserEntity userEntity = new UserEntity();
     userEntity.setUsername(username);
     userEntity.setPassword("user1");
     userEntity.setRole("USER"); // Set a non-null value for the role
     jpaUserRepository.save(userEntity);

     // When
     Optional<UserEntity> result = jpaUserRepository.findByUsername(username);

     // Then
     assertThat(result).isPresent();
     assertThat(result.get().getUsername()).isEqualTo(username);
 }

    @Test
    void testFindByUsername_NotFound() {
        // Given
        String username = "nonexistentuser";

        // When
        Optional<UserEntity> result = jpaUserRepository.findByUsername(username);

        // Then
        assertThat(result).isNotPresent();
    }
}