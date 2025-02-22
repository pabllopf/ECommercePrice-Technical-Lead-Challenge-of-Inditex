package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaPriceRepositoryAdapterTest {

    @Mock
    private IJpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private JpaPriceRepositoryAdapter jpaPriceRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

  @Test
  void testSave() {
      Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");
      PriceEntity priceEntity = PriceEntity.fromDomain(price);

      when(jpaPriceRepository.save(any(PriceEntity.class))).thenReturn(priceEntity);

      Price savedPrice = jpaPriceRepositoryAdapter.save(price);

      assertNotNull(savedPrice);
      assertEquals(price.getId(), savedPrice.getId());
  }

    @Test
    void testFindApplicablePrice() {
        Integer brandId = 1;
        Integer productId = 1;
        LocalDateTime date = LocalDateTime.now();
        PriceEntity priceEntity = new PriceEntity();

        when(jpaPriceRepository.findApplicablePriceEntity(brandId, productId, date)).thenReturn(Optional.of(priceEntity));

        Optional<Price> price = jpaPriceRepositoryAdapter.findApplicablePrice(brandId, productId, date);

        assertTrue(price.isPresent());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        PriceEntity priceEntity = new PriceEntity();

        when(jpaPriceRepository.findById(id)).thenReturn(Optional.of(priceEntity));

        Optional<Price> price = jpaPriceRepositoryAdapter.findById(id);

        assertTrue(price.isPresent());
    }

    @Test
    void testFindAll() {
        List<PriceEntity> priceEntities = List.of(new PriceEntity());

        when(jpaPriceRepository.findAll()).thenReturn(priceEntities);

        List<Price> prices = jpaPriceRepositoryAdapter.findAll();

        assertNotNull(prices);
        assertFalse(prices.isEmpty());
    }

    @Test
    void testUpdate() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");
        PriceEntity priceEntity = PriceEntity.fromDomain(price);

        when(jpaPriceRepository.findById(price.getId())).thenReturn(Optional.of(priceEntity));
        when(jpaPriceRepository.save(any(PriceEntity.class))).thenReturn(priceEntity);

        Optional<Price> updatedPrice = jpaPriceRepositoryAdapter.update(price);

        assertTrue(updatedPrice.isPresent());
        assertEquals(price.getId(), updatedPrice.get().getId());
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        PriceEntity priceEntity = new PriceEntity();

        when(jpaPriceRepository.findById(id)).thenReturn(Optional.of(priceEntity));

        boolean deleted = jpaPriceRepositoryAdapter.deleteById(id);

        assertTrue(deleted);
        verify(jpaPriceRepository, times(1)).deleteById(id);
    }
}