package com.pricecomparatormarket.models;

import com.pricecomparatormarket.models.entities.Product;

import java.time.LocalDate;

public class ProductVO {
    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private String store;
    private double price;
    private Integer discount;
    private LocalDate priceDate;

    public ProductVO(Product product, LocalDate priceDate) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productCategory = product.getProductCategory();
        this.brand = getBrand();
        this.store = product.getStore().getStoreName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.priceDate = priceDate;
    }

    public ProductVO() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDate getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(LocalDate priceDate) {
        this.priceDate = priceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
