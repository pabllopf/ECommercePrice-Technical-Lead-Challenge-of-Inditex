package dev.pabllopf.ecommerceprice.application.usecases.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IUpdatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.out.IPriceRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePriceUseCaseImpl implements IUpdatePriceUseCase {

    private final IPriceRepositoryPort priceRepositoryPort;

    @Override
    public void updatePrice(Price price) {
        priceRepositoryPort.update(price);
    }
}
