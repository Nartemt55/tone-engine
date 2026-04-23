package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import lombok.Builder;

@Builder
public record JwtAuthenticationDto(String token, String refreshToken) {
}
