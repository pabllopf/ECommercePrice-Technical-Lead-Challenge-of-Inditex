package dev.pabllopf.ecommerceprice.application.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    // The secret key used for signing and verifying JWT tokens.
    private static final String SECRET_KEY = "BcKvLsO2FsKAB8KtMcOsOMKJwojDn2TDucOgJsKsWcOzw7jCmUQKw613w5UdAcKGQg==";

    /**
     * This method is called for every HTTP request.
     * It checks if the request contains a valid JWT token and if the user is authenticated.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the "Authorization" header from the incoming HTTP request.
        String authHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and starts with "Bearer ".
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix from the token string

            try {
                // Decode the secret key and use it to sign the JWT token.
                Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

                // Parse the JWT token and retrieve the claims (information embedded in the token).
                Claims claims = Jwts.parser()
                        .setSigningKey(key) // Set the secret key for verification
                        .build()
                        .parseClaimsJws(token) // Parse the token
                        .getBody(); // Get the claims part from the token

                // Extract the username from the claims in the JWT token.
                String username = claims.getSubject();

                // If the username is found and the user is not authenticated yet,
                // we set the authentication in the SecurityContext.
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Create a UserDetails object with the username.
                    UserDetails userDetails = new User(username, "", Collections.emptyList());

                    // Create an authentication token with the user details.
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Set additional details such as the HTTP request details.
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the authentication in the SecurityContext so that the user is considered authenticated.
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // If any error occurs during the token parsing or verification, return an Unauthorized error response.
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        // Continue the filter chain for the next filter in the chain (or the actual endpoint if it's the last filter).
        filterChain.doFilter(request, response);
    }
}
