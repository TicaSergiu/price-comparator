package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.ProductVO;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.models.entities.Store;
import com.pricecomparatormarket.services.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/optimize-basket")
    public Map<Store, List<Product>> optimizeBasket(@RequestBody List<ProductVO> productLis) {
        return basketService.optimizeBasket(productLis);
    }

}
