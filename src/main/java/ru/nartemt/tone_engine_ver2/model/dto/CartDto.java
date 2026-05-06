package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Schema(description = "Информация о корзине")
public record CartDto(
        @Schema(description = "Идентификатор корзины")
        Long id,

        @Schema(description = "Список элементов корзины")
        List<CartItemDto> cartItems,

        @Schema(description = "Краткая информация о пользователе (id, имя)")
        UserShortDto user,

        @Schema(description = "Итоговая сумма всей корзины")
        BigDecimal totalPrice,

        @Schema(description = "Количество товара")
        Integer itemsAmount
) {
}