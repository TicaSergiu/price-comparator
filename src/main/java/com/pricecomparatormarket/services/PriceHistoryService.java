package com.pricecomparatormarket.services;

import com.pricecomparatormarket.models.ProductVO;
import com.pricecomparatormarket.models.entities.Discount;
import com.pricecomparatormarket.models.entities.Product;
import com.pricecomparatormarket.repositories.DiscountRepository;
import com.pricecomparatormarket.repositories.ProductRepository;
import com.pricecomparatormarket.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

@Service
public class PriceHistoryService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final DiscountRepository discountRepository;

    public PriceHistoryService(ProductRepository productRepository, StoreRepository storeRepository,
                               DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.discountRepository = discountRepository;
    }

    private void applyDiscountIfAvailable(ProductVO product) {
        List<Discount> discounts = discountRepository.getAllAvailableDiscountsOfProduct(product.getProductId(),
                                                                                        product.getPriceDate());

        discounts.stream()
                 .filter(d -> d.getStore().getStoreName().equals(product.getStore()))
                 .map(Discount::getPercentageDiscount)
                 .max(Integer::compareTo)
                 .ifPresent(product::setDiscount);
    }

    private Set<LocalDate> getRelevantDates(Product product, LocalDate from, LocalDate to) {
        Set<LocalDate> dates = new TreeSet<>();

        dates.add(product.getPriceDate());

        List<Discount> discounts = discountRepository.getHistoryOfProductDiscount(
                product.getProductId(),
                product.getStore().getId(),
                from,
                to
        );

        for (Discount discount : discounts) {
            if (!discount.getFromDate().isAfter(to)) {
                dates.add(discount.getFromDate());
            }
            if (!discount.getToDate().isBefore(from)) {
                dates.add(discount.getToDate());
            }
        }

        return dates;
    }

    private Product createProductForDate(Product baseProduct, LocalDate date) {
        Product newProduct = new Product();
        newProduct.setId(baseProduct.getId());
        newProduct.setProductId(baseProduct.getProductId());
        newProduct.setProductName(baseProduct.getProductName());
        newProduct.setProductCategory(baseProduct.getProductCategory());
        newProduct.setBrand(baseProduct.getBrand());
        newProduct.setPackageQuantity(baseProduct.getPackageQuantity());
        newProduct.setPackageUnit(baseProduct.getPackageUnit());
        newProduct.setPrice(baseProduct.getPrice());
        newProduct.setCurrency(baseProduct.getCurrency());
        newProduct.setStore(baseProduct.getStore());
        newProduct.setPriceDate(date);
        return newProduct;
    }

    public List<ProductVO> getProductPriceHistory(String productId, LocalDate from, LocalDate to,
                                                  Optional<String> store, Optional<String> category,
                                                  Optional<String> brand) {
        List<Product> products = productRepository.getProductsByProductId(productId);

        if (store.isPresent()) {
            products = products.stream()
                               .filter(p -> p.getStore().getStoreName().equals(store.get()))
                               .toList();
        }

        TreeMap<LocalDate, Map<String, ProductVO>> productsByDateAndStore = new TreeMap<>();

        for (Product product : products) {
            List<Product> storeHistory = productRepository.getProductHistoryByStore(productId,
                                                                                    product.getStore().getId(), from,
                                                                                    to);

            for (Product historyProduct : storeHistory) {
                Set<LocalDate> relevantDates = getRelevantDates(historyProduct, from, to);

                for (LocalDate date : relevantDates) {
                    ProductVO productForDate = new ProductVO(product, date);
                    applyDiscountIfAvailable(productForDate);

                    if (date.isAfter(LocalDate.now())) {
                        continue;
                    }

                    productsByDateAndStore.computeIfAbsent(date, k -> new HashMap<>())
                                          .put(productForDate.getStore(), productForDate);
                }
            }
        }

        return productsByDateAndStore.values().stream()
                                     .flatMap(storeProducts -> storeProducts.values().stream())
                                     .filter(p -> category.isEmpty() || p.getProductCategory().equals(category.get()))
                                     .filter(p -> brand.isEmpty() || p.getBrand().equals(brand.get()))
                                     .sorted(Comparator.comparing(ProductVO::getPriceDate))
                                     .toList();
    }

    public List<ProductVO> getStorePriceHistory(String store, LocalDate from, LocalDate to, Optional<String> category,
                                                Optional<String> brand) {

        Integer storeId = storeRepository.findAll()
                                         .stream()
                                         .filter(s -> s.getStoreName().equals(store))
                                         .findAny()
                                         .orElseThrow()
                                         .getId();

        List<Product> products = productRepository.getProductsHistoryByStore(storeId, from, to)
                                                  .stream()
                                                  .filter(p -> category.isEmpty() || p.getProductCategory()
                                                                                      .equals(category.get()))
                                                  .filter(p -> brand.isEmpty() || p.getBrand().equals(brand.get()))
                                                  .toList();

        TreeMap<LocalDate, Map<String, ProductVO>> productsByDateAndStore = new TreeMap<>();

        for (Product product : products) {
            List<Product> storeHistory = productRepository.getProductHistoryByStore(
                    product.getProductId(),
                    storeId,
                    from,
                    to
            );

            for (Product historyProduct : storeHistory) {
                Set<LocalDate> relevantDates = getRelevantDates(historyProduct, from, to);

                for (LocalDate date : relevantDates) {
                    ProductVO productForDate = new ProductVO(product, date);
                    applyDiscountIfAvailable(productForDate);
                    if (date.isAfter(LocalDate.now())) {
                        continue;
                    }

                    productsByDateAndStore.computeIfAbsent(date, k -> new HashMap<>())
                                          .put(productForDate.getStore(), productForDate);
                }
            }
        }

        // Convert to list and sort by date
        return productsByDateAndStore.values().stream()
                                     .flatMap(storeProducts -> storeProducts.values().stream())
                                     .sorted(Comparator.comparing(ProductVO::getPriceDate))
                                     .toList();
    }
} 