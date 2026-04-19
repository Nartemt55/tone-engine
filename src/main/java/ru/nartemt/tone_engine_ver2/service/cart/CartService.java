package ru.nartemt.tone_engine_ver2.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.CartItemMapper;
import ru.nartemt.tone_engine_ver2.model.dto.Cart;
import ru.nartemt.tone_engine_ver2.model.dto.CartDto;
import ru.nartemt.tone_engine_ver2.model.dto.CartItemDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.request.AddToCartRequest;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CartService {

    private final EquipmentCatalogService catalogService;
    private final Cart cart;
    private final CartItemMapper mapper;

    @Autowired
    public CartService(EquipmentCatalogService catalogService, Cart cart, CartItemMapper mapper) {
        this.catalogService = catalogService;
        this.cart = cart;
        this.mapper = mapper;
    }

    public void addToCart(AddToCartRequest request) {
        if (request.id() != null) {
            CartItemDto duplicate = cart.getCartItemDtos().stream()
                    .filter(e -> e.id() == request.id())
                    .findAny()
                    .orElse(null);
            if (duplicate != null) {
                CartItemDto updated = duplicate.toBuilder().quantity(duplicate.quantity() + request.quantity()).build();
                cart.getCartItemDtos().remove(duplicate);
                cart.getCartItemDtos().add(updated);
            }
            else {
                MusicalEquipment equipment = catalogService.findById(request.id());
                CartItemDto dto = mapper.toDto(equipment).toBuilder().quantity(request.quantity()).build();
                cart.getCartItemDtos().add(dto);
            }
        }
    }

    public void removeFromCart(Long id) {
        if (id != null)
            cart.getCartItemDtos().removeIf(i -> i.id() == id);
    }

    public void clearCart() {
        cart.getCartItemDtos().clear();
    }

    private CartItemDto getCartItemById(long id) {
        return cart.getCartItemDtos().stream()
                .filter(item -> item.id() == id)
                .findAny()
                .orElseThrow();
    }

    private BigDecimal getTotalPrice() {
        return cart.getCartItemDtos().stream()
                .map(i -> i.price().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private int getAmountOfItems() {
        return cart.getCartItemDtos().stream()
                .map(CartItemDto::quantity)
                .reduce(0, Integer::sum);
    }

    public CartDto getCartDto() {
        return new CartDto(cart.getCartItemDtos(),
                getTotalPrice(),
                getAmountOfItems()
        );
    }
}
