package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nartemt.tone_engine_ver2.model.dto.CartDto;
import ru.nartemt.tone_engine_ver2.model.request.AddToCartRequest;
import ru.nartemt.tone_engine_ver2.service.cart.CartService;

@RestController
@RequestMapping("/cart")
@Tag(name = "CartController")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public CartDto getCart() {
        return cartService.getCartDto();
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addToCart(@RequestBody @Valid AddToCartRequest request) {
        cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> handleClearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
