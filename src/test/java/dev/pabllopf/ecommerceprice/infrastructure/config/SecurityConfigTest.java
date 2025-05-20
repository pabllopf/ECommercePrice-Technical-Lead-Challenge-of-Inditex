package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.filters.JWTAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Mock
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private AuthenticationConfiguration authConfig;

    @InjectMocks
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    void testSecurityFilterChain() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class);
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(http);
        assertNotNull(securityFilterChain);
        verify(http, times(1)).csrf(any());
        verify(http, times(1)).headers(any());
        verify(http, times(1)).authorizeHttpRequests(any());
        verify(http, times(1)).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assert (passwordEncoder instanceof BCryptPasswordEncoder);
    }
}