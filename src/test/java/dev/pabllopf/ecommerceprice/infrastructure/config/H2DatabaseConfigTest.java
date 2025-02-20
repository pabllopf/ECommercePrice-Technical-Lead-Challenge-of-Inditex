package dev.pabllopf.ecommerceprice.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class H2DatabaseConfigTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldLoadH2DataSourceBean() {
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getClass().getName()).contains("HikariDataSource");
    }
}
