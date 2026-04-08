package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nartemt.tone_engine_ver2.service.cart.CartService;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {

        model.addAttribute("cart", cartService.getCartDto());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String handleAddToCart(@RequestParam("id") Long id) {
        cartService.addToCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String handleDeleteFromCart(@RequestParam("id") Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String handleClearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @PostMapping("/cart/increment")
    public String handleIncrementAmountOfItem(@RequestParam("id") Long id) {
        cartService.incrementAmountOfItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/decrement")
    public String handleDecrementAmountOfItem(@RequestParam("id") Long id) {
        cartService.decrementAmountOfItem(id);
        return "redirect:/cart";
    }
}
