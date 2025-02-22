package dev.pabllopf.ecommerceprice.domain.ports.in.auth;
public interface IAuthenticateUseCase {
    String authenticate(String username, String password);
}
