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

/**
 * The SecurityConfig class is responsible for configuring the security settings for the application.
 * It includes the setup for JWT authentication, password encoding, and access control for different API endpoints.
 * This configuration ensures that the application follows secure practices, including handling JWT tokens and password hashing.
 */
@Configuration
public class SecurityConfig {

    /**
     * The JWTAuthenticationFilter instance used to verify and authenticate JWT tokens in the request headers.
     */
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor for injecting the JWTAuthenticationFilter into the SecurityConfig class.
     *
     * @param jwtAuthenticationFilter The JWT filter responsible for token authentication.
     */
    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures HTTP security for the application. This includes enabling/disabling various security features,
     * setting up authorization rules, and defining which endpoints are public or require authentication.
     * It also adds the JWT authentication filter to intercept and validate the JWT token in the request headers.
     *
     * @param http The HttpSecurity object used to configure web security settings.
     * @return Configured SecurityFilterChain to apply the security settings.
     * @throws Exception If there are issues with the security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "api/auth/login",
                                "swagger-ui/**",
                                "/h2-console/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "build/reports/**",
                                "reports/**",
                                "test-reports/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Configures the AuthenticationManager used to handle authentication processes such as validating user credentials.
     * This manager is used by Spring Security to authenticate users.
     *
     * @param authConfig The AuthenticationConfiguration object used to configure authentication settings.
     * @return The configured AuthenticationManager.
     * @throws Exception If there are issues during the authentication manager configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures the PasswordEncoder used to hash and verify passwords.
     * This ensures that passwords are stored and processed securely by using the BCrypt hashing algorithm.
     *
     * @return The configured PasswordEncoder instance that uses BCrypt for password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
