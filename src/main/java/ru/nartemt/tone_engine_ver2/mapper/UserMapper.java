package ru.nartemt.tone_engine_ver2.mapper;

import org.mapstruct.Mapper;
import ru.nartemt.tone_engine_ver2.model.dto.UserShortDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserDto;
import ru.nartemt.tone_engine_ver2.model.entity.user.User;
import ru.nartemt.tone_engine_ver2.security.CustomUserDetails;

@Mapper(componentModel = "spring")
public interface UserMapper {
    CustomUserDetails toCustomUserDetails(User user);
    UserDto toDto(User entity);
    User toEntity(UserDto dto);
    UserShortDto toShortDto(User entity);
}
