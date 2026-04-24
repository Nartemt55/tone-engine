package ru.nartemt.tone_engine_ver2.model.request;

import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

public record RegistrationRequest(
        String name,
        String password,
        UserRole role
) { }
