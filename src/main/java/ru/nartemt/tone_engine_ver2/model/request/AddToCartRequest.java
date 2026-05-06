package ru.nartemt.tone_engine_ver2.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(description = "Данные запроса на добавление товара в козину")
public record AddToCartRequest(
        @Schema(description = "Идентификатор товара")
        @Min(1) Long id,

        @Schema(description = "Количество товара")
        @Min(1) Integer quantity
) {
}
