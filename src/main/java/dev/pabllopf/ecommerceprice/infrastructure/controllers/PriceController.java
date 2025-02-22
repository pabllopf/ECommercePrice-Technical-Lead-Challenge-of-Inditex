package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.application.dto.PriceResponseDto;
import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import dev.pabllopf.ecommerceprice.domain.model.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling price-related endpoints.
 * Exposes endpoints to retrieve product prices based on brand, product ID, and date.
 */
@RestController
@RequestMapping("api/prices")
@Tag(name = "Prices", description = "Endpoints for retrieving product prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    /**
     * Retrieves all prices.
     * This endpoint returns all price entries available in the system.
     *
     * @return A list of {@link PriceResponseDto} containing all price details.
     */
    @GetMapping("/all")
    @Operation(
            summary = "Get all prices",
            description = "Retrieves all available prices in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Prices found"),
            @ApiResponse(responseCode = "404", description = "No prices found", content = @Content)
    })
    public List<PriceResponseDto> getAllPrices() {
        List<Price> prices = priceService.findAllPrices();

        return prices.stream()
                .map(price -> new PriceResponseDto(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()))
                .toList();
    }

    /**
     * Retrieves a price by its ID.
     * This endpoint retrieves a specific price entry using the price ID.
     *
     * @param id The ID of the price.
     * @return A {@link PriceResponseDto} containing the price details.
     * @throws RuntimeException if no price is found for the given ID.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get price by ID",
            description = "Retrieves the price for a specific ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price found"),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)
    })
    public PriceResponseDto getPriceById(
            @PathVariable @Parameter(description = "Price ID", example = "1") Long id) {

        Optional<Price> price = priceService.findPriceById(id);

        return price.map(p -> new PriceResponseDto(
                        p.getProductId(),
                        p.getBrandId(),
                        p.getPriceList(),
                        p.getStartDate(),
                        p.getEndDate(),
                        p.getPrice()))
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }

    /**
     * Retrieves the applicable price for a given product, brand, and date.
     * This endpoint accepts brand ID, product ID, and a date in ISO-8601 format
     * and returns the applicable price for that product at the specified date and time.
     *
     * @param brandId   The ID of the brand.
     * @param productId The ID of the product.
     * @param date      The date in ISO-8601 format (e.g., "2020-06-14T10:00:00").
     * @return A {@link PriceResponseDto} containing the price details.
     * @throws RuntimeException if no price is found for the given parameters.
     */
    @GetMapping("/applicable")
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

        // Parse the date from string to LocalDateTime for processing
        LocalDateTime dateTime = LocalDateTime.parse(date);

        // Fetch applicable price using the service, map the result to a DTO, or throw exception if not found
        return priceService.findApplicablePrice(brandId, productId, dateTime)
                .map(price -> new PriceResponseDto(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()))
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }

    /**
     * Creates a new price entry.
     * This endpoint accepts the price details and creates a new price record in the database.
     *
     * @param price The price details to create.
     * @return The created {@link PriceResponseDto}.
     */
    @PostMapping
    @Operation(
            summary = "Create a new price",
            description = "Creates a new price entry for a product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Price details to create",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "\"brandId\": 1,\n" +
                                    "\"productId\": 35455,\n" +
                                    "\"priceList\": 1,\n" +
                                    "\"startDate\": \"2020-06-14T00:00:00\",\n" +
                                    "\"endDate\": \"2020-12-31T23:59:59\",\n" +
                                    "\"priority\": 0,\n" +
                                    "\"price\": 35.50,\n" +
                                    "\"currency\": \"EUR\"\n" +
                                    "}")
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Price created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid price data", content = @Content)
    })
    public PriceResponseDto createPrice(@RequestBody Price price) {
        Price createdPrice = priceService.createPrice(price);

        return new PriceResponseDto(
                createdPrice.getProductId(),
                createdPrice.getBrandId(),
                createdPrice.getPriceList(),
                createdPrice.getStartDate(),
                createdPrice.getEndDate(),
                createdPrice.getPrice());
    }

    /**
     * Updates an existing price entry.
     * This endpoint accepts price details and updates an existing price record.
     *
     * @param price The price details to update.
     * @return The updated {@link PriceResponseDto}.
     */
    @PutMapping
    @Operation(
            summary = "Update an existing price",
            description = "Updates an existing price entry for a product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Price details to update",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                    "\"id\": 1,\n" +
                                    "\"brandId\": 1,\n" +
                                    "\"productId\": 35455,\n" +
                                    "\"priceList\": 2,\n" +
                                    "\"startDate\": \"2020-06-14T15:00:00\",\n" +
                                    "\"endDate\": \"2020-06-14T18:30:00\",\n" +
                                    "\"priority\": 1,\n" +
                                    "\"price\": 25.45,\n" +
                                    "\"currency\": \"EUR\"\n" +
                                    "}")
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price updated successfully"),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)
    })
    public PriceResponseDto updatePrice(@RequestBody Price price) {
        priceService.updatePrice(price);

        return new PriceResponseDto(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }

    /**
     * Deletes a price entry.
     * This endpoint accepts the price ID and deletes the corresponding price record.
     *
     * @param id The ID of the price to delete.
     * @return A success message.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a price",
            description = "Deletes a price entry by its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)
    })
    public String deletePrice(@PathVariable Long id) {
        priceService.deletePrice(id);
        return "Price deleted successfully.";
    }
}