package com.pricecomparatormarket.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "store_name", nullable = false, length = Integer.MAX_VALUE)
    private String storeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Store store = (Store) o;
        return id.equals(store.id) && storeName.equals(store.storeName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + storeName.hashCode();
        return result;
    }
}