package dev.pabllopf.ecommerceprice.infrastructure.exception;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ResourceNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        String errorMessage = "Resource not found";

        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }
}
