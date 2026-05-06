package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Токен для обновления")
public record JwtRefreshDto(String refreshToken) {
}
