package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

public interface ICreatePriceUseCase {
    public Price createPrice(Price price);
}
