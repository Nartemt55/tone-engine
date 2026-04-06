package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nartemt.tone_engine_ver2.model.dto.Cart;
import ru.nartemt.tone_engine_ver2.model.dto.CartItem;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.service.ProductService;

@Controller
public class CartController {

    private final ProductService productService;
    private final Cart cart;

    @Autowired
    public CartController(ProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        model.addAttribute("cart", cart.getCartItems());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("itemsAmount", cart.getAmountOfItems());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String handleAddToCart(@RequestParam("id") Long id) {
        MusicalEquipment equipment = productService.getEquipmentById(id);
        if (equipment != null) {
            CartItem cartItem = new CartItem(
                    equipment.getId(),
                    equipment.getBrand(),
                    equipment.getModel(),
                    equipment.getImageUrl(),
                    equipment.getPrice()
            );
            cart.addToCart(cartItem);
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String handleDeleteFromCart(@RequestParam("id") Long id) {
        cart.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String handleClearCart() {
        cart.getCartItems().clear();
        return "redirect:/cart";
    }

    @PostMapping("/cart/increment")
    public String handleIncrementAmountOfItem(@RequestParam("id") Long id) {
        cart.incrementAmountOfItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/decrement")
    public String handleDecrementAmountOfItem(@RequestParam("id") Long id) {
        cart.decrementAmountOfItem(id);
        return "redirect:/cart";
    }
}
