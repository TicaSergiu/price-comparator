package com.pricecomparatormarket.services;

import com.pricecomparatormarket.models.entities.Discount;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.repositories.DiscountRepository;
import com.pricecomparatormarket.repositories.ProductRepository;
import org.springframework.stereotype.Service;

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

    public List<Product> getBestDiscounts(Optional<Integer> minDiscount) {
        return discountRepository.getAllAvailableDiscounts()
                                 .stream()
                                 .filter(discount -> discount.getPercentageDiscount() >= minDiscount.orElse(0))
                                 .map(discount -> {
                                     Product prod = productRepository.getProductByProductIdAndStore(
                                             discount.getProductId(), discount.getStore().getId());
                                     prod.setDiscount(discount.getPercentageDiscount());
                                     return prod;
                                 })
                                 .toList();
    }

    public List<Discount> getLatestDiscounts() {
        return discountRepository.getLatestDiscounts();
    }
}
