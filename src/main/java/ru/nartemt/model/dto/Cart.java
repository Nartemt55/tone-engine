package ru.nartemt.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addToCart(CartItem newItem) {
        for (CartItem item : cartItems) {
            if (item.getId() == newItem.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(newItem);
    }

    public void removeFromCart(long id) {
        cartItems.removeIf(i -> i.getId() == id);
    }

    public BigDecimal getTotalPrice() {
        return cartItems.stream()
                .map(CartItem::subTotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
