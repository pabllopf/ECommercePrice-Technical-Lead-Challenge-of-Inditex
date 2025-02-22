package dev.pabllopf.ecommerceprice.infrastructure.config;

import dev.pabllopf.ecommerceprice.application.services.auth.AuthenticateService;
import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ApplicationConfigTest {


    @Mock
    private IJpaPriceRepository jpaPriceRepository;

    @Mock
    private IUserRepositoryPort userRepositoryPort;

    @Mock
    private IJpaUserRepository jpaUserRepository;

    @Mock
    private ITokenProviderPort tokenProviderPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceService() {
        IPriceRepositoryPort priceRepositoryPort = mock(IPriceRepositoryPort.class);
        PriceService priceService = applicationConfig.priceService(priceRepositoryPort);
        assertNotNull(priceService);
    }

    @Test
    void testPriceRepositoryPort() {
        JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter = new JpaPriceRepositoryAdapter(jpaPriceRepository);
        IPriceRepositoryPort priceRepositoryPort = applicationConfig.priceRepositoryPort(jpaPriceRepositoryAdapter);
        assertNotNull(priceRepositoryPort);
    }

    @Test
    void testJpaPriceRepositoryAdapter() {
        JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter = applicationConfig.jpaPriceRepositoryAdapter(jpaPriceRepository);
        assertNotNull(jpaPriceRepositoryAdapter);
    }

    @Test
    void testAuthenticateService() {
        AuthenticateService authenticateService = applicationConfig.authenticateService(mock(IAuthenticatePort.class));
        assertNotNull(authenticateService);
    }

    @Test
    void testAuthenticatePort() {
        IAuthenticatePort authenticatePort = applicationConfig.authenticatePort(userRepositoryPort, tokenProviderPort, passwordEncoder);
        assertNotNull(authenticatePort);
    }

    @Test
    void testTokenProviderPort() {
        ITokenProviderPort tokenProviderPort = applicationConfig.tokenProviderPort();
        assertNotNull(tokenProviderPort);
    }

    @Test
    void testUserRepositoryPort() {

        JpaUserRepositoryAdapter jpaUserRepositoryAdapter = new JpaUserRepositoryAdapter(jpaUserRepository);
        IUserRepositoryPort userRepositoryPort = applicationConfig.userRepositoryPort(jpaUserRepository);
        assertNotNull(userRepositoryPort);
    }
}