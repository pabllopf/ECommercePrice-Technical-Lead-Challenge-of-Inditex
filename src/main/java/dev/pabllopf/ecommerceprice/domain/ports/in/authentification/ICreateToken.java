package dev.pabllopf.ecommerceprice.domain.ports.in.authentification;

import dev.pabllopf.ecommerceprice.domain.model.Token;

public interface ICreateToken {
    Token createToken(String username, String password);
}
