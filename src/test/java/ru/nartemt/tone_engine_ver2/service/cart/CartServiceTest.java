package ru.nartemt.tone_engine_ver2.service.cart;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.nartemt.tone_engine_ver2.mapper.CartItemMapper;
import ru.nartemt.tone_engine_ver2.model.dto.Cart;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

public class CartServiceTest {

    @Mock
    private EquipmentCatalogService catalogService;
    @Mock
    private Cart cart;
    @Mock
    private CartItemMapper mapper;

    @InjectMocks
    private CartService cartService;


}
