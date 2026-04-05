package ru.nartemt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.model.dto.Cart;
import ru.nartemt.model.dto.CartItem;
import ru.nartemt.model.entity.MusicalEquipment;
import ru.nartemt.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/cart")
    public String getCartPage() {
        return "cart";
    }

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    public String handleAddToCart(@RequestParam("id") Long id,
                                  HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

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

    @RequestMapping(value = "/cart/remove", method = RequestMethod.POST)
    public String handleDeleteFromCart(@RequestParam("id") Long id,
                                       HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.removeFromCart(id);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }
}
