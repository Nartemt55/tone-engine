package ru.nartemt.tone_engine_ver2.model.dto;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.math.BigDecimal;
import java.util.List;

public record AdvisorResponseDto(Album album, List<MusicalEquipment> equipmentList,
                                List<Integer> frequencies, BigDecimal totalPrice) {
}

