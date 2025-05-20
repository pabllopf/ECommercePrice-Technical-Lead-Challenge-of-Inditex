package dev.pabllopf.ecommerceprice.application.services.price;

import dev.pabllopf.ecommerceprice.domain.model.Price;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.ICreatePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IDeletePriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IGetPriceUseCase;
import dev.pabllopf.ecommerceprice.domain.ports.in.price.IUpdatePriceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private ICreatePriceUseCase createPrice;

    @Mock
    private IGetPriceUseCase getPrice;

    @Mock
    private IUpdatePriceUseCase updatePrice;

    @Mock
    private IDeletePriceUseCase deletePrice;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePrice() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");
        when(createPrice.createPrice(price)).thenReturn(price);

        Price createdPrice = priceService.createPrice(price);

        assertEquals(price, createdPrice);
        verify(createPrice).createPrice(price);
    }

    @Test
    public void testFindAllPrices() {
        List<Price> prices = List.of(new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD"));
        when(getPrice.findAllPrices()).thenReturn(prices);

        List<Price> result = priceService.findAllPrices();

        assertEquals(prices, result);
        verify(getPrice).findAllPrices();
    }

    @Test
    public void testFindPriceById() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");
        when(getPrice.findPriceById(1L)).thenReturn(Optional.of(price));

        Optional<Price> result = priceService.findPriceById(1L);

        assertEquals(Optional.of(price), result);
        verify(getPrice).findPriceById(1L);
    }

    @Test
    public void testUpdatePrice() {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now(), 100, 2, 2, new BigDecimal(2), "USD");

        priceService.updatePrice(price);

        verify(updatePrice).updatePrice(price);
    }

    @Test
    public void testDeletePrice() {
        Long id = 1L;

        priceService.deletePrice(id);

        verify(deletePrice).deletePrice(id);
    }
}