package dev.pabllopf.ecommerceprice.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * LoginRequest is used to encapsulate the login credentials provided by a user.
 * It contains the username and password that are sent from the client to authenticate the user.
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}
