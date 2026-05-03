package ru.nartemt.tone_engine_ver2.model.request;

import jakarta.validation.constraints.NotBlank;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

public record RegistrationRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Password is required") String password,
        UserRole role
) { }
