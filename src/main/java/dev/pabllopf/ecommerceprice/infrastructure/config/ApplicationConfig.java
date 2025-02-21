package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import dev.pabllopf.ecommerceprice.application.usecases.price.CreatePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.DeletePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.GetPriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.UpdatePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.adapters.JpaPriceRepositoryAdapter;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    /**
     * Configures and provides the PriceService bean.
     * This service coordinates the different use cases related to price management,
     * including creating, getting, updating, and deleting price records.
     *
     * @param priceRepositoryPort The price repository port, injected by Spring.
     * @return A configured instance of PriceService.
     */
    @Bean
    public PriceService priceService(IPriceRepositoryPort priceRepositoryPort) {
        return new PriceService(
                new CreatePriceUseCaseImpl(priceRepositoryPort), // Use case for creating prices
                new GetPriceUseCaseImpl(priceRepositoryPort),    // Use case for retrieving prices
                new UpdatePriceUseCaseImpl(priceRepositoryPort), // Use case for updating prices
                new DeletePriceUseCaseImpl(priceRepositoryPort)  // Use case for deleting prices
        );
    }

    /**
     * Provides the PriceRepositoryPort bean.
     * This is the interface that the application uses to interact with the price data repository.
     * It delegates the actual data persistence logic to JpaPriceRepositoryAdapter.
     *
     * @param jpaPriceRepositoryAdapter The JPA repository adapter, injected by Spring.
     * @return The IPriceRepositoryPort interface, which is implemented by JpaPriceRepositoryAdapter.
     */
    @Bean
    public IPriceRepositoryPort priceRepositoryPort(JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter) {
        return jpaPriceRepositoryAdapter;
    }

    /**
     * Configures and provides the JpaPriceRepositoryAdapter bean.
     * The adapter converts between the domain model (Price) and the JPA entity (PriceEntity).
     * It interacts with the JPA repository to perform CRUD operations.
     *
     * @param jpaPriceRepository The JPA repository, injected by Spring.
     * @return A configured instance of JpaPriceRepositoryAdapter.
     */
    @Bean
    public JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter(IJpaPriceRepository jpaPriceRepository) {
        return new JpaPriceRepositoryAdapter(jpaPriceRepository);
    }
}
