package ru.nartemt.tone_engine_ver2.service.scoring;

import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

public interface ScoringRule<E> {
    double calculate(E equipment, Preset preset);
}
