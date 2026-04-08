package ru.nartemt.tone_engine_ver2.mapper;

import org.mapstruct.Mapper;
import ru.nartemt.tone_engine_ver2.model.dto.CartItemDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemDto toDto(MusicalEquipment equipment);
}
