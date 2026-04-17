package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.repository.PedalRepository;
import ru.nartemt.tone_engine_ver2.model.entity.pedal.Pedal;

@Service
public class PedalService extends AbstractEquipmentService<Pedal, PedalRepository> {
    @Autowired
    public PedalService(PedalRepository pedalRepository, ScoringConfig scoringConfig) {
        super(pedalRepository, Pedal.class, scoringConfig);
    }

    @Override
    public double calculateScore(Pedal pedal, Album album) {
        double score = 0;
        var config = scoringConfig.getScoring();
        Preset preset = album.getPreset();

        if (pedal.getGainLevel() >= preset.getMinGainRequired())
            score += config.getBonus().getGainMatch();
        else
            score -= 50.0;

        double midMatch = 100 - Math.abs(preset.getFreqMid() - pedal.getBaseMidResponce());
        score += midMatch * config.getFrequency().getMid();

        return score;
    }
}
