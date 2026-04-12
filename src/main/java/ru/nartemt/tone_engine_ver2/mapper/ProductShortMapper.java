package ru.nartemt.tone_engine_ver2.mapper;

import org.mapstruct.Mapper;
import ru.nartemt.tone_engine_ver2.model.dto.ProductShortDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductShortMapper {
    ProductShortDto toDto(MusicalEquipment entity);
    List<ProductShortDto> toDtoList(List<MusicalEquipment> entityList);
}
