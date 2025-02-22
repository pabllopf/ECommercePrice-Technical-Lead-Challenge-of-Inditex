package dev.pabllopf.ecommerceprice.infrastructure.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The OpenAPIConfig class provides the configuration for the OpenAPI documentation of the E-commerce Price API.
 * It includes the metadata of the API, such as the title, version, description, and contact information,
 * as well as the security settings for API access.
 * This configuration enables the integration of OpenAPI documentation into the Spring Boot application,
 * providing a clear and structured description of the API endpoints.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Configures the custom OpenAPI instance for the E-commerce Price API.
     * This method sets up the metadata for the API, such as the title, version, description, and contact details.
     * Additionally, it configures the security scheme using Bearer Authentication with JWT tokens.
     *
     * @return The OpenAPI configuration object with the custom metadata and security settings.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce Price API")
                        .version("1.0")
                        .description("API to manage pricing in e-commerce")
                        .contact(new Contact()
                                .name("Pablo Perdomo Falcón")
                                .email("pabloperdomofalcon@gmail.com")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new io.swagger.v3.oas.models.security.SecurityScheme()
                                .type(Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
