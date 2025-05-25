package com.pricecomparatormarket.repositories;

import com.pricecomparatormarket.models.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
    Alert getAlertByAlertId(String alertId);
}
