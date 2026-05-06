package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Креды пользователя")
public record UserCredentialsDto(
        @Schema(description = "Имя")
        @JsonProperty(namespace = "username") @NotBlank(message = "Заполните поле имя") String name,

        @Schema(description = "Пароль")
        @NotBlank(message = "Заполните поле пароль") String password
) {
}
