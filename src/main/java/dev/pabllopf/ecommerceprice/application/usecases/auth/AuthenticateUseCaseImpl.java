package dev.pabllopf.ecommerceprice.application.usecases.auth;

import dev.pabllopf.ecommerceprice.domain.ports.in.auth.IAuthenticateUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IAuthenticatePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticateUseCaseImpl implements IAuthenticateUseCase {

    private final IAuthenticatePort authenticatePort;

    @Override
    public String authenticate(String username, String password) {
        return authenticatePort.authenticate(username, password);
    }
}
