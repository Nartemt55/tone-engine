package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Schema(description = "Информация об элементе корзины")
public record CartItemDto(
        @Schema(description = "Идентификатор элемента корзины")
        Long id,

        @Schema(description = "Брэнд товара")
        String brand,

        @Schema(description = "Модель товара")
        String model,

        @Schema(description = "Ссылка на фото товара")
        String imageUrl,

        @Schema(description = "Цена позиции (цена товара * количество)")
        BigDecimal price,

        @Schema(description = "Количество выбранного товара")
        Integer quantity
) {
}
