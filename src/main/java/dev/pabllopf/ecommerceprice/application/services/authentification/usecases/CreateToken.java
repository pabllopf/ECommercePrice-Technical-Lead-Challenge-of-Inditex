package dev.pabllopf.ecommerceprice.application.services.authentification.usecases;

import dev.pabllopf.ecommerceprice.domain.model.Token;
import dev.pabllopf.ecommerceprice.domain.ports.in.authentification.ICreateToken;

public class CreateToken implements ICreateToken {
    @Override
    public Token createToken(String username, String password) {
        return new Token("token");
    }
}
