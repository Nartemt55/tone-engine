package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.service.base.AbstractBaseService;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractEquipmentService<E extends MusicalEquipment, R extends JpaRepository<E, Long>>
        extends AbstractBaseService<E, Long, R>
        implements ProductProvider<E> {

    private final Class<E> entityClass;
    private final ScoringConfig scoringConfig;

    protected AbstractEquipmentService(R repository, Class<E> entityClass, ScoringConfig scoringConfig) {
        super(repository);
        this.entityClass = entityClass;
        this.scoringConfig = scoringConfig;
    }

    @Override
    public boolean isSupport(String equipmentType) {
        return entityClass.getSimpleName().equalsIgnoreCase(equipmentType);
    }

    @Override
    public List<E> findMatches(Preset preset, BigDecimal maxPrice) {
        return repository.findAll().stream()
                .filter(equipment -> equipment.getStock() > 0)
                .filter(equipment -> equipment.getPrice().compareTo(maxPrice) <= 0)
                .sorted((equipment1, equipment2) ->
                        Double.compare(calculateScore(equipment2, preset), calculateScore(equipment1, preset)))
                .limit(5)
                .toList();
    }

    public double calculateScore(E equipment, Preset preset) {
        double score = 0;
        var config = scoringConfig.getScoring();

        double highMatch = 100 - Math.abs(equipment.getBaseTrebleResponse() - preset.getFreqHigh());
        double midMatch = 100 - Math.abs(50 - preset.getFreqMid());
        double lowMatch = 100 - Math.abs(50 - preset.getFreqLow());

        score += highMatch * config.getFrequency().getHigh();
        score += midMatch * config.getFrequency().getMid();
        score += lowMatch * config.getFrequency().getLow();

    }

}
