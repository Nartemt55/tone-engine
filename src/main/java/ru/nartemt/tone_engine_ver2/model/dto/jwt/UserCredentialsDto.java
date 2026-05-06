package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Креды пользователя")
public record UserCredentialsDto(
        @Schema(description = "Имя")
        @JsonProperty(namespace = "username") String name,

        @Schema(description = "Пароль")
        String password
) {
}
