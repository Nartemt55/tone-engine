package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(
            summary = "Получить корзину",
            description = "Возвращает корзину пользователя"
    )
    @GetMapping
    public CartDto getCart() {
        return cartService.getCartDto();
    }

    @Operation(
            summary = "Добавить в корзину",
            description = "Принимает AddToCartRequest, добавляет заданное количество quantity товара id в корзину пользователя"
    )
    @PostMapping("/items")
    public ResponseEntity<Void> addToCart(@RequestBody @Valid AddToCartRequest request) {
        cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Удалить товар из корзины",
            description = "Удаляет товар id из пользовательской корзины"

    )
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Очистить корзину",
            description = "Удаляет все элементы из корзины пользователя"
    )
    @DeleteMapping("/items")
    public ResponseEntity<Void> handleClearCart() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
