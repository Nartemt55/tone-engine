package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.BodyShape;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.repository.GuitarRepository;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;

@Service
public class GuitarService extends AbstractEquipmentService<Guitar, GuitarRepository> {
    @Autowired
    public GuitarService(GuitarRepository guitarRepository, ScoringConfig scoringConfig) {
        super(guitarRepository, Guitar.class, scoringConfig);
    }
    @Override
    public double calculateScore(Guitar guitar, Preset preset) {
        double score = 0;
        var config = scoringConfig.getScoring();

        if (guitar.getBrand().equalsIgnoreCase(preset.getAlbum().getTargetBrand())) {
            score += 500.0; // Брендовое соответствие важнее всего
        }

        if (guitar.getBodyShape() == preset.getAlbum().getTargetShape()) {
            score += 300.0; // Форма (например, Flying V) дает огромный буст
        }

        // 2. Датчики (строгое условие)
        if (guitar.getPickupConfig() == preset.getReqPickup()) {
            score += config.getBonus().getPickupMatch();
        } else {
            score -= 200.0; // Если нужны хамбакеры, а впаривают синглы — это не тот звук
        }

        double trebleDiff = Math.abs(guitar.getBaseTrebleResponse() - preset.getFreqHigh());
        score += (100 - trebleDiff) * config.getFrequency().getHigh();

        return score;
    }

}
