package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.Product;
import com.pricecomparatormarket.models.ShoppingList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/product/getAll")
    public List<Object> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/product/get")
    public List<Object> getFilteredProducts(@RequestParam String name, @RequestParam String category, @RequestParam String brand) {
        return new ArrayList<>();
    }

    @GetMapping("/optimize-basket")
    public List<ShoppingList> optimizeBasket(@RequestBody List<Product> productList) {
        return new ArrayList<>();
    }

    @GetMapping("/product/{id}/price-history")
    public List<Object> getProductPriceHistory(@PathVariable String id, @RequestParam Optional<String> store, @RequestParam Optional<String> category, @RequestParam Optional<String> brand) {
        return new ArrayList<>();
    }


    /**
     * Creates an alert for a product
     *
     * @param id    the id of the product
     * @param rules the rule for the alert, either a target price or a percentage discount
     */
    @PostMapping("/product/{id}/create-alert")
    public void createProductAlert(@PathVariable String id, @RequestParam Map<String, String> rules) {
        //TODO: probably set an alert to the database
    }
}
