package ru.nartemt.tone_engine_ver2.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

@Schema(description = "Данные запроса на регистрацию")
public record RegistrationRequest(
        @Schema(description = "Имя пользователя")
        @NotBlank(message = "Заполните поле имя") String name,

        @Schema(description = "Пароль пользователя")
        @NotBlank(message = "Заполните поле пароль") String password,

        @Schema(description = "Роль пользователя")
        UserRole role
) { }
