package ru.nartemt.tone_engine_ver2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.nartemt.tone_engine_ver2.model.dto.CartItemDto;
import ru.nartemt.tone_engine_ver2.model.entity.cart.CartItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(target = "brand", source = "product.brand")
    @Mapping(target = "model", source = "product.model")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    @Mapping(target = "price", source = "product.price")
    CartItemDto toDto(CartItem entity);
    List<CartItemDto> toDtoList(List<CartItem> entityList);
}
