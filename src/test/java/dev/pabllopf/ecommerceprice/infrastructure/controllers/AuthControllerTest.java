package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.application.services.auth.AuthenticateService;
import dev.pabllopf.ecommerceprice.infrastructure.entities.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticateService authenticateService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("user1");

        when(authenticateService.authenticate("user1", "user1")).thenReturn("mocked-jwt-token");

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"user1\", \"password\": \"user1\"}"))
                .andExpect(status().isOk());
    }
}