package dev.pabllopf.ecommerceprice.infrastructure.controller;

import dev.pabllopf.ecommerceprice.application.dto.PriceResponseDto;
import dev.pabllopf.ecommerceprice.domain.service.PriceService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public PriceResponseDto getPrice(@RequestParam Integer brandId,
                                     @RequestParam Integer productId,
                                     @RequestParam String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return priceService.getPrice(brandId, productId, dateTime)
                .map(price -> new PriceResponseDto(price.getProductId(), price.getBrandId(),
                        price.getPriceList(), price.getStartDate(), price.getEndDate(), price.getPrice()))
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }
}
