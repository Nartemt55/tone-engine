package ru.nartemt.tone_engine_ver2.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private BigDecimal totalPrice;
    private int itemsAmount;

    public CartDto(List<CartItemDto> cartItems, BigDecimal totalPrice, int itemsAmount) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.itemsAmount = itemsAmount;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(int itemsAmount) {
        this.itemsAmount = itemsAmount;
    }
}


