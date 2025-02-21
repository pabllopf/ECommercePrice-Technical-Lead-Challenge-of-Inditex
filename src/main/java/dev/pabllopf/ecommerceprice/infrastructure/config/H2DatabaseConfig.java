package dev.pabllopf.ecommerceprice.infrastructure.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

@Configuration
public class H2DatabaseConfig {

    /**
     * Configures a DataSource bean for connecting to an H2 in-memory database.
     * This configuration is used for testing or development purposes.
     *
     * @return A DataSource object configured to connect to the H2 in-memory database.
     */
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create() // Creates a new DataSourceBuilder instance
                .url("jdbc:h2:mem:ecommerceprice;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE") // URL for the in-memory H2 database
                .driverClassName("org.h2.Driver") // The driver class name for H2
                .username("sa") // Username for the database connection
                .password("") // Password for the database connection (empty in this case)
                .build(); // Builds and returns the DataSource instance
    }
}
