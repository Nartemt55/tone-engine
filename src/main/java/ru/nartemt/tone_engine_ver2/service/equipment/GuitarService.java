package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
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

        if (guitar.getPickupConfig() == preset.getReqPickup())
            score += config.getBonus().getPickupMatch();

        double trebleDiff = Math.abs(guitar.getBaseTrebleResponse() - preset.getFreqHigh());
        score += (100 - trebleDiff) * config.getFrequency().getHigh();

        if (guitar.getOutputPower() >= preset.getRequiredOutputPower())
            score += scoringConfig.getScoring().getOutputPower();

        return score;
    }



}
