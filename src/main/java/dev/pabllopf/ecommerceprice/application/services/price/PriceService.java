package dev.pabllopf.ecommerceprice.application.services.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final IPriceRepositoryPort priceRepository;

    public PriceService(IPriceRepositoryPort priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime date) {
        return priceRepository.findApplicablePrice(brandId, productId, date);
    }
}
