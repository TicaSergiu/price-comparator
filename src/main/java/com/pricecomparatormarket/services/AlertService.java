package com.pricecomparatormarket.services;

import com.pricecomparatormarket.models.entities.Alert;
import com.pricecomparatormarket.models.entities.Discount;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.models.entities.Store;
import com.pricecomparatormarket.repositories.AlertRepository;
import com.pricecomparatormarket.repositories.DiscountRepository;
import com.pricecomparatormarket.repositories.ProductRepository;
import com.pricecomparatormarket.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;


    public AlertService(AlertRepository alertRepository, StoreRepository storeRepository,
                        ProductRepository productRepository, DiscountRepository discountRepository) {
        this.alertRepository = alertRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }


    public Alert createAlert(Map<String, String> value) {
        String productId = value.get("product_id");
        Store store = null;
        Integer minDiscount = null;
        Double minPrice = null;

        if (value.containsKey("store")) {
            store = storeRepository.findById(Integer.parseInt(value.get("store"))).get();
        }
        if (value.containsKey("min_discount")) {
            minDiscount = Integer.parseInt(value.get("min_discount"));
        }
        if (value.containsKey("min_price")) {
            minPrice = Double.parseDouble(value.get("min_price"));
        }

        Alert alert = new Alert();
        alert.setAlertId("id2");
        alert.setProductId(productId);
        alert.setStore(store);
        alert.setDiscount(minDiscount);
        alert.setPrice(minPrice);

        return alertRepository.save(alert);
    }

    public boolean alertUser(String id) {
        Alert alert = alertRepository.getAlertByAlertId(id);
        List<Product> products = productRepository.getProductsByProductId(alert.getProductId());
        Discount discount = discountRepository.getDiscountByProductId(alert.getProductId());

        long size = products.stream()
                            .filter(product -> product.getPrice() <= alert.getPrice() || discount.getPercentageDiscount() >= alert.getDiscount())
                            .count();
        return size > 0;
    }
}
