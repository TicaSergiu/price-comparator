package com.pricecomparatormarket.repositories;

import com.pricecomparatormarket.models.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
