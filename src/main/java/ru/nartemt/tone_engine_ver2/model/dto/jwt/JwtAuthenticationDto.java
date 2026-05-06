package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "JWT токены")
public record JwtAuthenticationDto(
        @Schema(description = "Токен авторизации")
        String token,

        @Schema(description = "Токен для обновления")
        String refreshToken
) {
}
