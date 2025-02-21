package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.filters.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // JWT authentication filter used for verifying JWT tokens
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    // Constructor to inject the JWT filter into the security configuration
    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures the HTTP security settings for the application.
     * Disables CSRF, configures headers, and sets up authorization rules for various endpoints.
     *
     * @param http The HttpSecurity object used to configure web security.
     * @return Configured SecurityFilterChain for the application.
     * @throws Exception If there are errors in security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disables CSRF protection (usually for stateless APIs)
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Disables frame options (for H2 Console usage)
                .authorizeHttpRequests(auth -> auth
                        // Whitelisting specific endpoints (permit all access to them)
                        .requestMatchers(
                                "api/auth/login", // Login endpoint
                                "swagger-ui/**",  // Swagger UI documentation endpoints
                                "/h2-console/**", // H2 console endpoints (in-memory database interface)
                                "/v3/api-docs/**", // Swagger API docs endpoint
                                "/swagger-ui.html" // Swagger UI HTML page
                        ).permitAll() // Permit all access to the above endpoints
                        .anyRequest().authenticated() // Require authentication for any other requests
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Adds the JWT filter before the default username-password filter
        return http.build(); // Return the configured security filter chain
    }

    /**
     * Configures the AuthenticationManager to handle authentication operations.
     *
     * @param authConfig The AuthenticationConfiguration object used to configure authentication settings.
     * @return The configured AuthenticationManager.
     * @throws Exception If there are issues with the authentication configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // Get the default authentication manager
    }

    /**
     * Configures the PasswordEncoder to use BCrypt hashing for password security.
     *
     * @return The configured PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder for password hashing
    }
}
