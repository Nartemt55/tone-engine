package ru.nartemt.tone_engine_ver2.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация об альбоме")
public record AlbumPreviewDto(
        @Schema(description = "Идентификатор альбома")
        long id,

        @Schema(description = "Название альбома")
        String title,

        @Schema(description = "Имя исполнителя / псевдоним / название группы")
        String artist,

        @Schema(description = "Ссылка на обложку альбома")
        String coverUrl
) {
}

