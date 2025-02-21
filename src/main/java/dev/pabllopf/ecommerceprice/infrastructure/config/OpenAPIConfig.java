package dev.pabllopf.ecommerceprice.infrastructure.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    /**
     * Configures OpenAPI documentation for the E-commerce Price API.
     * This method sets up the metadata and security schemes for the API documentation.
     *
     * @return The OpenAPI configuration with custom details for the API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Information section of the API, including the title, version, description, and contact info.
                .info(new Info()
                        .title("E-commerce Price API") // The title of the API
                        .version("1.0") // The version of the API
                        .description("API para gestionar precios en e-commerce") // Short description in Spanish: "API for managing prices in e-commerce"
                        .contact(new Contact() // Contact details of the author or maintainer
                                .name("Pablo Perdomo Falcón") // Author's name
                                .email("pabloperdomofalcon@gmail.com"))) // Author's email address
                // Define security requirement for the API, using Bearer authentication with JWT tokens
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // Set up the components section to define security schemes
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new io.swagger.v3.oas.models.security.SecurityScheme()
                                .type(Type.HTTP) // Use HTTP-based authentication
                                .scheme("bearer") // Bearer token authentication scheme
                                .bearerFormat("JWT"))); // Format for the bearer token (JWT)
    }
}
