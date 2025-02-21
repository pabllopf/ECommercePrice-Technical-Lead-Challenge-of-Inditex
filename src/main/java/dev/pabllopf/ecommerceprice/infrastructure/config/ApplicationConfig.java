package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.mapper.PriceMapper;
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

    @Bean
    public PriceService priceService(IPriceRepositoryPort priceRepositoryPort) {
        return new PriceService(
                new CreatePriceUseCaseImpl(priceRepositoryPort),
                new GetPriceUseCaseImpl(priceRepositoryPort),
                new UpdatePriceUseCaseImpl(priceRepositoryPort),
                new DeletePriceUseCaseImpl(priceRepositoryPort)
        );
    }

    @Bean
    public IPriceRepositoryPort priceRepositoryPort(JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter) {
        return jpaPriceRepositoryAdapter;
    }

    @Bean
    public JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter(IJpaPriceRepository jpaPriceRepository) {
        return new JpaPriceRepositoryAdapter(jpaPriceRepository);
    }
}
