package dev.pabllopf.ecommerceprice.application.services.auth;

import dev.pabllopf.ecommerceprice.domain.ports.in.auth.IAuthenticateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService implements IAuthenticateUseCase {

    private final IAuthenticateUseCase authenticateUseCase;

    @Override
    public String authenticate(String username, String password) {
        return authenticateUseCase.authenticate(username, password);
    }
}
