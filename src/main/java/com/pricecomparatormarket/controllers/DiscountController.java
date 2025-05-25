package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.entities.Discount;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.services.DiscountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/discount/best-discounts")
    public List<Product> getBestDiscounts(@RequestParam Optional<Integer> minDiscount) {
        return discountService.getBestDiscounts(minDiscount);
    }

    @GetMapping("/discount/latest")
    public List<Discount> getLatestDiscount() {
        return discountService.getLatestDiscounts();
    }
}
