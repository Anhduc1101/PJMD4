package com.ra.model.entity;

public class Product {
    private int productId;
private String productName;
private Category category;
private String description;
private double unitPrice;
private int stock;
private boolean status;

    public Product() {
    }

    public Product(int productId, String productName, Category category, String description, double unitPrice, int stock, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
