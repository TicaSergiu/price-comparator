package com.pricecomparatormarket.repositories;


import com.pricecomparatormarket.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> getAllByProductId(String productId);

    @Query(value = "SELECT * FROM discount WHERE product_id=?1 and from_date<=?2 and to_date>=?2", nativeQuery = true)
    Set<Discount> getAllProductsWithDiscount(String productId, LocalDate date);
}
