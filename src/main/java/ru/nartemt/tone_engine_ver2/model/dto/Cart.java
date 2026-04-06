package ru.nartemt.tone_engine_ver2.model.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@SessionScope
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

    public CartItem getCartItemById(long id) {
        return cartItems.stream()
                .filter(item -> item.getId() == id)
                .findAny()
                .orElse(null);
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

    public void decrementAmountOfItem(long id) {
        CartItem item = getCartItemById(id);
        if (item.getQuantity() == 1)
            cartItems.remove(item);
        if (item.getQuantity() > 1)
            item.decrement();
    }

    public void incrementAmountOfItem(long id) {
        CartItem item = getCartItemById(id);
        item.increment();
    }

    public int getAmountOfItems() {
        return cartItems.stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
