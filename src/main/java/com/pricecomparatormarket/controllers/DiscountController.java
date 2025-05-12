package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DiscountController {

    @GetMapping("/discount/best-discounts")
    public List<Product> getBestDiscounts() {
        return new ArrayList<>();
    }

    @GetMapping("/discount/latest")
    public List<Product> getLatestDiscount() {
        return new ArrayList<>();
    }
}
