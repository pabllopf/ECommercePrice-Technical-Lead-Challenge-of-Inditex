package dev.pabllopf.ecommerceprice.application.services.auth;

import dev.pabllopf.ecommerceprice.domain.model.Token;
import dev.pabllopf.ecommerceprice.domain.ports.in.auth.ICreateToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements ICreateToken
{
    @Override
    public Token createToken(String username, String password) {
        return new Token("");
    }
}
