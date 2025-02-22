package dev.pabllopf.ecommerceprice.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OpenAPIConfigTest {

    @Test
    void testCustomOpenAPI() {
        OpenAPIConfig openAPIConfig = new OpenAPIConfig();
        OpenAPI openAPI = openAPIConfig.customOpenAPI();

        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);

    }
}