package com.pricecomparatormarket.models;

public class AlertVO {
    private String alertId;
    private String productId;
    private double minPrice;
    private int minTarget;

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public int getMinTarget() {
        return minTarget;
    }

    public void setMinTarget(int minTarget) {
        this.minTarget = minTarget;
    }
}
