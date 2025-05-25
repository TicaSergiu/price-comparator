package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.ProductVO;
import com.pricecomparatormarket.services.PriceHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class PriceHistoryController {
    private final PriceHistoryService priceHistoryService;

    public PriceHistoryController(PriceHistoryService priceHistoryService) {
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping("/price-history/{id}")
    public List<ProductVO> getProductPriceHistory(@PathVariable String id, @RequestParam LocalDate from,
                                                  @RequestParam LocalDate to, @RequestParam Optional<String> store,
                                                  @RequestParam Optional<String> category,
                                                  @RequestParam Optional<String> brand) {
        return priceHistoryService.getProductPriceHistory(id, from, to, store, category, brand);
    }

    @GetMapping("/price-history/store/{store}")
    public List<ProductVO> getStorePriceHistory(@PathVariable String store, @RequestParam LocalDate from,
                                                @RequestParam LocalDate to, @RequestParam Optional<String> category,
                                                @RequestParam Optional<String> brand) {

        return priceHistoryService.getStorePriceHistory(store, from, to, category, brand);
    }
}
