package dev.pabllopf.ecommerceprice.application.services.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.ICreatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IDeletePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IGetPriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IUpdatePriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PriceService implements ICreatePriceUseCase, IDeletePriceUseCase, IGetPriceUseCase, IUpdatePriceUseCase {

    private final ICreatePriceUseCase createPrice;
    private final IGetPriceUseCase getPrice;
    private final IUpdatePriceUseCase updatePrice;
    private final IDeletePriceUseCase deletePrice;

    @Override
    public void deletePrice(Long id) {
        deletePrice.deletePrice(id);
    }

    @Override
    public List<Price> findAllPrices() {
        return getPrice.findAllPrices();
    }

    @Override
    public Optional<Price> findPriceById(Long id) {
        return getPrice.findPriceById(id);
    }

    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return getPrice.findApplicablePrice(brandId, productId, date);
    }

    @Override
    public void updatePrice(Price price) {
        updatePrice.updatePrice(price);
    }

    @Override
    public Price createPrice(Price price) {
        return createPrice.createPrice(price);
    }
}
