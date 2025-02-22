package dev.pabllopf.ecommerceprice.infrastructure.controllers;

import dev.pabllopf.ecommerceprice.application.dto.PriceResponseDto;
import dev.pabllopf.ecommerceprice.application.services.price.PriceService;
import dev.pabllopf.ecommerceprice.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
    void testGetAllPrices() throws Exception {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 1, 0, BigDecimal.valueOf(100), "USD");
        when(priceService.findAllPrices()).thenReturn(Collections.singletonList(price));

        mockMvc.perform(get("/api/prices/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1));
    }

    @Test
    void testGetPriceById() throws Exception {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 1, 0, BigDecimal.valueOf(100), "USD");
        when(priceService.findPriceById(1L)).thenReturn(Optional.of(price));

        mockMvc.perform(get("/api/prices/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1));
    }


    @Test
    void testGetPrice() throws Exception {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 1, 0, BigDecimal.valueOf(100), "USD");
        when(priceService.findApplicablePrice(1, 1, LocalDateTime.parse("2020-06-14T10:00:00"))).thenReturn(Optional.of(price));

        mockMvc.perform(get("/api/prices/applicable")
                .param("brandId", "1")
                .param("productId", "1")
                .param("date", "2020-06-14T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1));
    }

    @Test
    void testUpdatePrice() throws Exception {
        Price price = new Price(1L, 1, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 1, 0, BigDecimal.valueOf(100), "USD");

        mockMvc.perform(put("/api/prices")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"brandId\": 1, \"productId\": 1, \"priceList\": 1, \"startDate\": \"2020-06-14T00:00:00\", \"endDate\": \"2020-12-31T23:59:59\", \"priority\": 0, \"price\": 100, \"currency\": \"USD\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1));
    }

    @Test
    void testDeletePrice() throws Exception {
        mockMvc.perform(delete("/api/prices/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Price deleted successfully."));
    }
}