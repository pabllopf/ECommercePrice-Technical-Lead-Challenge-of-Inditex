package dev.pabllopf.ecommerceprice.domain.service;

import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @Mock
    private IPriceRepositoryPort priceRepository;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPriceNotFound() {
        Integer brandId = 1;
        Integer productId = 100;
        LocalDateTime date = LocalDateTime.of(2025, 2, 20, 10, 0);

        when(priceRepository.findApplicablePrice(brandId, productId, date)).thenReturn(Optional.empty());

        Optional<Price> actualPrice = priceService.getPrice(brandId, productId, date);

        assertEquals(Optional.empty(), actualPrice);
    }
}