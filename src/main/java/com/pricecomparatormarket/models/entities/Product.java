package com.pricecomparatormarket.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "product_id", nullable = false, length = Integer.MAX_VALUE)
    private String productId;

    @Column(name = "product_name", nullable = false, length = Integer.MAX_VALUE)
    private String productName;

    @Column(name = "product_category", nullable = false, length = Integer.MAX_VALUE)
    private String productCategory;

    @Column(name = "brand", nullable = false, length = Integer.MAX_VALUE)
    private String brand;

    @Column(name = "package_quantity", nullable = false)
    private Double packageQuantity;

    @Column(name = "package_unit", nullable = false, length = Integer.MAX_VALUE)
    private String packageUnit;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "currency", nullable = false, length = Integer.MAX_VALUE)
    private String currency;


    @Column(name = "price_date")
    private LocalDate priceDate;

    @Transient
    private Integer discount = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store", nullable = false)
    private Store store;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Double packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(LocalDate priceDate) {
        this.priceDate = priceDate;
    }

}