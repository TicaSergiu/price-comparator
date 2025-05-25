package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.ProductVO;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.models.entities.Store;
import com.pricecomparatormarket.services.BasketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final BasketService basketService;

    public ProductController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/product/getAll")
    public List<Object> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("/product/get")
    public List<Object> getFilteredProducts(@RequestParam String productId) {
        return new ArrayList<>();
    }

    @GetMapping("/optimize-basket")
    public Map<Store, List<Product>> optimizeBasket(@RequestBody List<ProductVO> productLis) {
        return basketService.optimizeBasket(productLis);
    }

}
