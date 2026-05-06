package ru.nartemt.tone_engine_ver2.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

@Schema(description = "Данные запроса на регистрацию")
public record RegistrationRequest(
        @Schema(description = "Имя пользователя")
        @NotBlank(message = "Name is required") String name,

        @Schema(description = "Пароль пользователя")
        @NotBlank(message = "Password is required") String password,

        @Schema(description = "Роль пользователя")
        UserRole role
) { }
