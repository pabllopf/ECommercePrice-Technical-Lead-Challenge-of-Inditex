package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.application.dto.PriceResponseDto;
import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/prices")
@Tag(name = "Prices", description = "Endpoints for retrieving product prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Retrieves the applicable price for a given product, brand, and date.
     *
     * @param brandId   The ID of the brand.
     * @param productId The ID of the product.
     * @param date      The date and time in ISO-8601 format (e.g., "2024-02-20T10:30:00").
     * @return A {@link PriceResponseDto} containing the price details.
     * @throws RuntimeException if no price is found.
     */
    @GetMapping
    @Operation(
            summary = "Get applicable price",
            description = "Retrieves the applicable price for a product at a specific date and time."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price found"),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)
    })
    public PriceResponseDto getPrice(
            @RequestParam @Parameter(description = "Brand ID", example = "1") Integer brandId,
            @RequestParam @Parameter(description = "Product ID", example = "35455") Integer productId,
            @RequestParam @Parameter(description = "Date in ISO format", example = "2020-06-14T10:00:00") String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        return priceService.getPrice(brandId, productId, dateTime)
                .map(price -> new PriceResponseDto(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()))
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }
}
