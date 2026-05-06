package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

@Builder
@Schema(description = "Данные пользователя")
public record UserDto(
        @Schema(description = "Идентификатор")
        Long id,

        @Schema(description = "Имя")
        String name,

        @Schema(description = "Пароль")
        String password,

        @Schema(description = "Роль")
        UserRole role
) {
}
