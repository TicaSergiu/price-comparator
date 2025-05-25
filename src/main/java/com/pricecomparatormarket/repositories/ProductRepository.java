package com.pricecomparatormarket.repositories;

import com.pricecomparatormarket.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getProductsByProductId(String productId);

    @Query(value = "SELECT * FROM product WHERE product_id=?1 and store=?2 ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Product getProductByProductIdAndStore(String productId, Integer store);

    @Query(value = "SELECT * FROM product WHERE product_id=?1 and store=?2 AND price_date BETWEEN (SELECT price_date from product where product_id=?1 AND store=?2 AND price_date<=?3 ORDER BY price_date DESC LIMIT 1) AND ?4 ORDER BY price_date ", nativeQuery = true)
    List<Product> getProductHistoryByStore(String productId, Integer store, LocalDate from, LocalDate to);

    @Query(value = "SELECT * FROM product p WHERE store=?1 AND price_date BETWEEN(SELECT price_date FROM product WHERE p.id=id AND store=?1 AND p.price_date<=?3 ORDER BY price_date DESC LIMIT 1) AND ?4 ORDER BY price_date", nativeQuery = true)
    List<Product> getProductsHistoryByStore(Integer store, LocalDate from, LocalDate to);
}
