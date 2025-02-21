package dev.pabllopf.ecommerceprice.infrastructure.adapters;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import dev.pabllopf.ecommerceprice.infrastructure.entities.PriceEntity;
import dev.pabllopf.ecommerceprice.infrastructure.repositories.IJpaPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaPriceRepositoryAdapter implements IPriceRepositoryPort {

    private final IJpaPriceRepository jpaPriceRepository;

    @Override
    public Price save(Price price) {
        return jpaPriceRepository.save(PriceEntity.fromDomain(price)).toDomain();
    }

    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return jpaPriceRepository.findApplicablePriceEntity(brandId, productId, date).map(PriceEntity::toDomain);
    }

    @Override
    public Optional<Price> findById(Long id) {
        return jpaPriceRepository.findById(id).map(PriceEntity::toDomain);
    }

    @Override
    public List<Price> findAll() {
        return jpaPriceRepository.findAll().stream().map(PriceEntity::toDomain).toList();
    }

    @Override
    public Optional<Price> update(Price price) {
        return jpaPriceRepository.findById(price.getId())
                .map(priceEntity -> jpaPriceRepository
                        .save(PriceEntity.fromDomain(price))
                        .toDomain());
    }

    @Override
    public boolean deleteById(Long id) {
        return jpaPriceRepository.findById(id)
                .map(priceEntity -> {
                    jpaPriceRepository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}
