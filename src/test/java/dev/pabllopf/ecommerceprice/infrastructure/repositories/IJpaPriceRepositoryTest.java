package dev.pabllopf.ecommerceprice.infrastructure.repositories;

import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class IJpaPriceRepositoryTest {

    @Autowired
    private IJpaPriceRepository jpaPriceRepository;

    @Test
    void testFindApplicablePriceEntity() {
        // Given
        Integer brandId = 1;
        Integer productId = 1;
        LocalDateTime date = LocalDateTime.of(2023, 10, 1, 0, 0);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setBrandId(brandId);
        priceEntity.setProductId(productId);
        priceEntity.setStartDate(LocalDateTime.of(2023, 9, 1, 0, 0));
        priceEntity.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        priceEntity.setPriority(1);
        priceEntity.setPrice(BigDecimal.valueOf(100.0));
        priceEntity.setPriceList(1); // Set a value for priceList

        jpaPriceRepository.save(priceEntity);

        // When
        Optional<PriceEntity> result = jpaPriceRepository.findApplicablePriceEntity(brandId, productId, date);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualTo(BigDecimal.valueOf(100.0));
    }
}