package dev.pabllopf.ecommerceprice.domain.ports.out.auth;
public interface ITokenProviderPort {
    String generateToken(String username);
}
