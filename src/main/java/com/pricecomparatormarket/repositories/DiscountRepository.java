package com.pricecomparatormarket.repositories;


import com.pricecomparatormarket.models.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    @Query(value = "SELECT * FROM discount WHERE product_id=?1 and from_date<=?2 and to_date>=?2", nativeQuery = true)
    List<Discount> getAllAvailableDiscountsOfProduct(String productId, LocalDate date);

    @Query(value = "SELECT * FROM discount WHERE from_date<=NOW()::date AND to_date>=NOW()::date ORDER BY percentage_discount DESC", nativeQuery = true)
    List<Discount> getAllAvailableDiscounts();

    @Query(value = "SELECT * FROM discount WHERE from_date<=NOW() AND to_date>=NOW() AND id NOT IN (SELECT id FROM discount WHERE from_date<=NOW()::date-1)", nativeQuery = true)
    List<Discount> getLatestDiscounts();

    @Query(value = "SELECT * FROM discount WHERE product_id=?1 AND store_id=?2 AND " +
            "((from_date <= ?4 AND to_date >= ?3) OR " +  // Discount overlaps with the period
            "(from_date BETWEEN ?3 AND ?4) OR " +         // Discount starts within the period
            "(to_date BETWEEN ?3 AND ?4)) " +             // Discount ends within the period
            "ORDER BY from_date", nativeQuery = true)
    List<Discount> getHistoryOfProductDiscount(String productId, Integer store, LocalDate from, LocalDate to);

}
