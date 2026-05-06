package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация о пользователе")
public record UserShortDto(
        @Schema(description = "Идентификатор пользователя")
        Long id,

        @Schema(description = "Имя пользователя")
        String name
) {
}
