package dev.pabllopf.ecommerceprice.infrastructure.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class H2DatabaseConfigTest {

    @InjectMocks
    private H2DatabaseConfig h2DatabaseConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDataSource() {
        DataSource dataSource = h2DatabaseConfig.dataSource();
        assertNotNull(dataSource);
    }
}