package ru.nartemt.tone_engine_ver2.model.dto;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private final List<CartItemDto> cartItemDtos;

    public Cart() {
        cartItemDtos = new ArrayList<>();
    }

    public List<CartItemDto> getCartItemDtos() {
        return cartItemDtos;
    }
}
