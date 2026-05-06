package ru.nartemt.tone_engine_ver2.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;

@Schema(description = "Данные для фильтрации списка товаров")
public record CatalogFilterRequest(
        @Schema(description = "Тип отображаемых товаров")
        EquipmentType type,

        @Schema(description = "Порядок сортировки")
        String sort
) {
}
