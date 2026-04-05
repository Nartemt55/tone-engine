package ru.nartemt.model.dto;

import java.math.BigDecimal;

public class CartItem {

    private long id;
    private String brand;
    private String model;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    public CartItem() { }

    public CartItem(long id, String brand, String model, String imageUrl, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = 1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFullName() {
        return brand + " " + model;
    }

    public BigDecimal subTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
