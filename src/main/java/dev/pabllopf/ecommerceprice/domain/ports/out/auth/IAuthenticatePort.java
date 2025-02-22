package dev.pabllopf.ecommerceprice.domain.ports.out.auth;

public interface IAuthenticatePort {
    String authenticate(String username, String password);
}
