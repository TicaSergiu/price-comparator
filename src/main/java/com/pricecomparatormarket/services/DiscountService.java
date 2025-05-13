package com.pricecomparatormarket.services;

import com.pricecomparatormarket.models.Discount;
import com.pricecomparatormarket.models.Product;
import com.pricecomparatormarket.repositories.DiscountRepository;
import com.pricecomparatormarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public DiscountService(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    /**
     * Gets the best discounts that are available at the time of interogation
     *
     * @param length      the number of elements to be returned, default 10
     * @param minDiscount minimum percentage of the discount to return
     * @return List of products that have the discount bigger than minDiscount
     */
    public List<Product> getBestDiscounts(Optional<Integer> length, Optional<Integer> minDiscount) {
        return discountRepository.getAllAvailableDiscounts()
                                 .stream()
                                 .filter(discount -> discount.getPercentageDiscount() >= minDiscount.orElse(0))
                                 .sorted(Comparator.comparing(Discount::getPercentageDiscount)
                                                   .reversed())
                                 .limit(length.orElse(10))
                                 .map(discount -> productRepository.getProductByProductIdAndStore(discount.getProductId(), discount.getStore()))
                                 .toList();
    }

    public List<Discount> getLatestDiscounts() {
        return discountRepository.getLatestDiscounts();
    }
}
