package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.services.auth.AuthenticateService;
import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import dev.pabllopf.ecommerceprice.application.usecases.auth.AuthenticateUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.CreatePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.DeletePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.GetPriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.application.usecases.price.UpdatePriceUseCaseImpl;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IAuthenticatePort;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.ITokenProviderPort;
import dev.pabllopf.ecommerceprice.domain.ports.out.auth.IUserRepositoryPort;
import dev.pabllopf.ecommerceprice.domain.ports.out.price.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.adapters.AuthenticateAdapter;
import dev.pabllopf.ecommerceprice.infrastructure.adapters.JpaPriceRepositoryAdapter;
import dev.pabllopf.ecommerceprice.infrastructure.adapters.JpaUserRepositoryAdapter;
import dev.pabllopf.ecommerceprice.infrastructure.adapters.JwtTokenAdapter;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The ApplicationConfig class is responsible for configuring the application-level services,
 * use cases, and repository adapters. This configuration ensures that the necessary beans
 * for handling price management and authentication are provided and properly wired together.
 * It includes the setup for services such as price management and user authentication, as well as
 * the necessary repository ports and adapters for interacting with the persistence layer.
 */
@Configuration
public class ApplicationConfig {

    /**
     * Configures and provides the PriceService bean.
     * The PriceService is responsible for coordinating the different use cases related to price management,
     * including creating, getting, updating, and deleting price records.
     *
     * @param priceRepositoryPort The price repository port, injected by Spring.
     * @return A configured instance of PriceService.
     */
    @Bean
    public PriceService priceService(IPriceRepositoryPort priceRepositoryPort) {
        return new PriceService(
                new CreatePriceUseCaseImpl(priceRepositoryPort),
                new GetPriceUseCaseImpl(priceRepositoryPort),
                new UpdatePriceUseCaseImpl(priceRepositoryPort),
                new DeletePriceUseCaseImpl(priceRepositoryPort)
        );
    }

    /**
     * Provides the PriceRepositoryPort bean.
     * The PriceRepositoryPort is the interface that the application uses to interact with the price data repository.
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

    /**
     * Provides the AuthenticateService bean.
     * The AuthenticateService coordinates the authentication process by handling the authentication use case.
     * It uses the AuthenticateUseCaseImpl to process user login and token generation.
     *
     * @param authenticatePort The port responsible for handling authentication, injected by Spring.
     * @return A configured instance of AuthenticateService.
     */
    @Bean
    public AuthenticateService authenticateService(IAuthenticatePort authenticatePort) {
        return new AuthenticateService(
                new AuthenticateUseCaseImpl(authenticatePort)
        );
    }

    /**
     * Provides the AuthenticatePort bean.
     * The AuthenticatePort interface handles the user authentication logic, including user retrieval and token generation.
     * It delegates the actual logic to the AuthenticateAdapter.
     *
     * @param userRepositoryPort The user repository port, injected by Spring.
     * @param tokenProviderPort  The token provider port, injected by Spring.
     * @param passwordEncoder    The password encoder, injected by Spring.
     * @return A configured instance of AuthenticateAdapter, implementing the AuthenticatePort.
     */
    @Bean
    public IAuthenticatePort authenticatePort(IUserRepositoryPort userRepositoryPort, ITokenProviderPort tokenProviderPort, PasswordEncoder passwordEncoder) {
        return new AuthenticateAdapter(userRepositoryPort, passwordEncoder, tokenProviderPort);
    }

    /**
     * Provides the TokenProviderPort bean.
     * The TokenProviderPort is responsible for generating and validating JWT tokens for user authentication.
     * It delegates the actual logic to JwtTokenAdapter.
     *
     * @return A configured instance of JwtTokenAdapter, implementing the ITokenProviderPort interface.
     */
    @Bean
    public ITokenProviderPort tokenProviderPort() {
        return new JwtTokenAdapter();
    }

    /**
     * Provides the UserRepositoryPort bean.
     * The UserRepositoryPort is the interface responsible for interacting with the user data repository.
     * It delegates the actual data retrieval and persistence to JpaUserRepositoryAdapter.
     *
     * @param jpaUserRepository The JPA user repository, injected by Spring.
     * @return A configured instance of JpaUserRepositoryAdapter, implementing the IUserRepositoryPort interface.
     */
    @Bean
    public IUserRepositoryPort userRepositoryPort(IJpaUserRepository jpaUserRepository) {
        return new JpaUserRepositoryAdapter(jpaUserRepository);
    }
}
