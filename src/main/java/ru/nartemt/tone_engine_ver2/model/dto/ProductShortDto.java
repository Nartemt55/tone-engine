package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(description = "Краткая информация о товаре")
public record ProductShortDto(
        @Schema(description = "Идентификатор товара")
        Long id,

        @Schema(description = "Брэнд товара")
        String brand,

        @Schema(description = "Модель товара")
        String model,

        @Schema(description = "Ссылка на фото товара")
        String imageUrl,

        @Schema(description = "Цена позиции товара")
        BigDecimal price
) {
}