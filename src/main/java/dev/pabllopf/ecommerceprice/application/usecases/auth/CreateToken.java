package dev.pabllopf.ecommerceprice.application.usecases.auth;

import dev.pabllopf.ecommerceprice.domain.model.Token;
import dev.pabllopf.ecommerceprice.domain.ports.in.auth.ICreateToken;

public class CreateToken implements ICreateToken {

    @Override
    public Token createToken(String username, String password) {
        return null;
    }
}
