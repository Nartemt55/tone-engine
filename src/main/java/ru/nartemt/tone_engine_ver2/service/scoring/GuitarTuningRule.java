package ru.nartemt.tone_engine_ver2.service.scoring;

import org.springframework.stereotype.Component;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

@Component
public class GuitarTuningRule implements ScoringRule<Guitar> {
    @Override
    public double calculate(Guitar guitar, Preset preset) {
        return (guitar.getTuning())
    }
}
