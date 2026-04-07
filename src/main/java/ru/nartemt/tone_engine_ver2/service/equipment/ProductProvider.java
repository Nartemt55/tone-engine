package ru.nartemt.tone_engine_ver2.service.equipment;

import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

import java.math.BigDecimal;
import java.util.List;

public interface ProductProvider<T> {
    boolean isSupport(String equipmentType);
    double calculateScore(T equipment, Preset preset);
    List<T> findMatches(Preset preset, BigDecimal maxPrice);
}
