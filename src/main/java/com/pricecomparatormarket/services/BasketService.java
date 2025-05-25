package com.pricecomparatormarket.services;

import com.pricecomparatormarket.models.ProductVO;
import com.pricecomparatormarket.models.entities.Discount;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.models.entities.Store;
import com.pricecomparatormarket.repositories.DiscountRepository;
import com.pricecomparatormarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BasketService {
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;


    public BasketService(ProductRepository productRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }

    public Map<Store, List<Product>> optimizeBasket(List<ProductVO> basket) {
        Map<Store, List<Product>> shoppingLists = new HashMap<>();
        for (ProductVO product : basket) {
            List<Product> products = productRepository.getProductsByProductId(product.getProductId());
            Product cheapest = getCheapestProduct(products, product.getProductId());
            Store store = cheapest.getStore();
            if (shoppingLists.containsKey(store)) {
                shoppingLists.get(store).add(cheapest);
            } else {
                shoppingLists.put(store, new ArrayList<>());
                shoppingLists.get(store).add(cheapest);
            }
        }

        return shoppingLists;
    }

    private Product getCheapestProduct(List<Product> products, String productId) {
        List<Discount> discounts = discountRepository.getAllAvailableDiscountsOfProduct(productId,
                                                                                        LocalDate.now().minusDays(7));

        return products.stream().peek(p -> {
            Optional<Discount> discount = Optional.empty();
            for (Discount d : discounts) {
                if (d.getStore().equals(p.getStore())) {
                    discount = Optional.of(d);
                }
            }
            if (discount.isPresent()) {
                double price = p.getPrice();
                double newPrice = price - (price * discount.get().getPercentageDiscount() / 100);
                p.setPrice(newPrice);
            }
        }).min(Comparator.comparing(Product::getPrice)).get();
    }

}
