package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

import dev.pabllopf.ecommerceprice.domain.model.User;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface IUserRepositoryPort {
    Optional<User> findByUsername(String username);
}

