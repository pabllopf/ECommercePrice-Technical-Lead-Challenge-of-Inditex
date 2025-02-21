package dev.pabllopf.ecommerceprice.domain.model;

/**
 * Represents an authentication or authorization token.
 * This record is an immutable representation of a token value.
 *
 * @param value The token string.
 */
public record Token(String value){}
