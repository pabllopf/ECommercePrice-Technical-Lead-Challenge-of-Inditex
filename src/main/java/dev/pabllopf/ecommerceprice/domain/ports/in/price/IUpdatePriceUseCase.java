package dev.pabllopf.ecommerceprice.domain.ports.in.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;

import java.math.BigDecimal;

public interface IUpdatePriceUseCase {

    public void updatePrice(Price price);
}
