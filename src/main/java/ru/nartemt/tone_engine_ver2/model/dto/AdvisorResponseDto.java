package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Ответ умной системы подбора оборудования")
public record AdvisorResponseDto
        (
                @Schema(description = "Выбранный пользователем альбом")
                Album album,

                @Schema(description = "Список рекомендованного оборудования")
                List<MusicalEquipment> equipmentList,

                @Schema(description = "Список частот для настройки")
                List<Integer> frequencies,

                @Schema(description = "Итоговая сумма всего рекомендованного оборудования")
                @Min(0) BigDecimal totalPrice
        ) {
}

