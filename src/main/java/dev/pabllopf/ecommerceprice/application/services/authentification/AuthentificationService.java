package dev.pabllopf.ecommerceprice.application.services.authentification;

import dev.pabllopf.ecommerceprice.application.services.authentification.usecases.CreateToken;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService
{
    private CreateToken createToken;

    public boolean login(String username, String password) {
        return createToken.createToken(username, password) != null;
    }
}
