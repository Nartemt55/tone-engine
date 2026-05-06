package ru.nartemt.tone_engine_ver2.service.cart;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.CartItemMapper;
import ru.nartemt.tone_engine_ver2.mapper.UserMapper;
import ru.nartemt.tone_engine_ver2.model.dto.CartDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.cart.Cart;
import ru.nartemt.tone_engine_ver2.model.entity.cart.CartItem;
import ru.nartemt.tone_engine_ver2.model.entity.user.User;
import ru.nartemt.tone_engine_ver2.model.request.AddToCartRequest;
import ru.nartemt.tone_engine_ver2.repository.UserRepository;
import ru.nartemt.tone_engine_ver2.repository.cart.CartRepository;
import ru.nartemt.tone_engine_ver2.security.SecurityUtils;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final EquipmentCatalogService catalogService;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final SecurityUtils utils;
    private final CartItemMapper cartItemMapper;
    private final UserMapper userMapper;

    private Optional<Cart> findByUserIdWithItems(Long userId) {
        return cartRepository.findByUserIdWithItems(userId);
    }

    private BigDecimal countTotalPrice(Cart cart) {
        log.debug("Count total price of cart items");
        return cart.getItems()
                .stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Transactional
    public Cart getCartOrCreate() {
        User user = userRepository.findById(utils.getCurrentUserId())
                .orElseThrow(() -> new AccessDeniedException("You must login before get a cart!"));

        return findByUserIdWithItems(user.getId())
                .orElseGet(() -> {
                            log.debug("User hasn't cart yet. Create new cart");
                            Cart cart = new Cart(user, new ArrayList<>());
                            return cartRepository.saveAndFlush(cart);
                        }
                );
    }

    @Transactional
    public CartDto getCartDto() {
        Cart cart = getCartOrCreate();
        return CartDto.builder()
                .id(cart.getId())
                .cartItems(cartItemMapper.toDtoList(cart.getItems()))
                .user(userMapper.toShortDto(cart.getUser()))
                .totalPrice(countTotalPrice(cart))
                .itemsAmount(cart.getItems().size())
                .build();
    }

    @Transactional
    public void addToCart(AddToCartRequest request) {
        Cart cart = getCartOrCreate();
        MusicalEquipment equipment = catalogService.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Equipment with " + request.id() + " not found"));

        Optional<CartItem> foundedItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == equipment.getId())
                .findFirst();

        if (foundedItem.isPresent()) {
            log.debug("Item already in cart, update quantity");
            CartItem item = foundedItem.get();
            item.setQuantity(item.getQuantity() + request.quantity());
        } else {
            CartItem item = new CartItem(
                    request.quantity(),
                    cart,
                    equipment
            );
            cart.getItems().add(item);
        }
    }

    @Transactional
    public void removeFromCart(Long productId) {
        Cart cart = getCartOrCreate();
        cart.getItems().removeIf(item -> item.getProduct().getId() == productId);
    }

    @Transactional
    public void clearCart() {
        Cart cart = getCartOrCreate();
        cart.getItems().clear();
    }
}
