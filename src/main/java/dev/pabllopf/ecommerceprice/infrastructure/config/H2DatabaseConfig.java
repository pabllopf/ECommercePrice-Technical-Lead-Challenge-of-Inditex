package dev.pabllopf.ecommerceprice.infrastructure.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

/**
 * The H2DatabaseConfig class is responsible for configuring a DataSource bean
 * to connect to an H2 in-memory database. This configuration is typically used
 * for testing or development environments where an in-memory database is preferred.
 * The class defines the connection details for the H2 database, such as the URL,
 * driver class, and authentication details.
 */
@Configuration
public class H2DatabaseConfig {

    /**
     * Configures a DataSource bean to connect to the H2 in-memory database.
     * This method returns a DataSource object that can be used by Spring
     * to establish a connection to the H2 database. It is typically used
     * for in-memory databases during the testing or development phase.
     *
     * @return A configured DataSource object for the H2 in-memory database.
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:ecommerceprice;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();
    }
}
