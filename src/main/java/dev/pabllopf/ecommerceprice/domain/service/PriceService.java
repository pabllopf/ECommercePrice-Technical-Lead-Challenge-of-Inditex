package dev.pabllopf.ecommerceprice.domain.service;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.repository.IPriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceService {

    private final IPriceRepository priceRepository;

    public PriceService(IPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> getPrice(Integer brandId, Integer productId, LocalDateTime date) {
        return priceRepository.findApplicablePrice(brandId, productId, date);
    }
}
