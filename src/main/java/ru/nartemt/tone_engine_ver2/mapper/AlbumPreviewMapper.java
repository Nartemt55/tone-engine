package ru.nartemt.tone_engine_ver2.mapper;

import org.mapstruct.Mapper;
import ru.nartemt.tone_engine_ver2.model.dto.AlbumPreviewDto;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumPreviewMapper {
    AlbumPreviewDto toDto(Album entity);
    List<AlbumPreviewDto> toDtoList(List<Album> entityList);
}
