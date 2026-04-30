package ru.nartemt.tone_engine_ver2.model.dto.jwt;

import lombok.Builder;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;

@Builder
public record UserDto(Long id, String name, String password, UserRole role) {
}
