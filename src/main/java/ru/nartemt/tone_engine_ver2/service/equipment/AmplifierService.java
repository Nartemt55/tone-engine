package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.entity.amplifier.AmplifierType;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.repository.AmplifierRepository;
import ru.nartemt.tone_engine_ver2.model.entity.amplifier.Amplifier;


@Service
public class AmplifierService extends AbstractEquipmentService<Amplifier, AmplifierRepository> {
    @Autowired
    public AmplifierService(AmplifierRepository amplifierRepository, ScoringConfig scoringConfig) {
        super(amplifierRepository, Amplifier.class, scoringConfig);
    }

    @Override
    public double calculateScore(Amplifier amplifier, Preset preset) {
        double score = 0;
        var config = scoringConfig.getScoring();

        double warmthDiff = Math.abs((double) amplifier.getWarmthScore() - preset.getWarmthTarget());
        score += (100 - warmthDiff) * config.getWarmth();

        if ((preset.getWarmthTarget() < 30 && amplifier.getAmplifierType() == AmplifierType.SOLID_STATE) ||
                (preset.getWarmthTarget() > 70 && amplifier.getAmplifierType() == AmplifierType.TUBE)) {
            score += 40.0;
        }

        if (amplifier.getOutputPower() < preset.getRequiredOutputPower()) {
            score -= 50.0;
        }

        return score;
    }
}
