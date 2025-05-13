package com.pricecomparatormarket.repositories;

import com.pricecomparatormarket.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
