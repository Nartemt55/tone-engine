package ru.nartemt.tone_engine_ver2.service.equipment;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.nartemt.tone_engine_ver2.config.ScoringConfig;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.repository.specification.EquipmentSpecification;
import ru.nartemt.tone_engine_ver2.service.base.AbstractBaseService;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractEquipmentService<E extends MusicalEquipment,
        R extends JpaRepository<E, Long> & JpaSpecificationExecutor<E>>
        extends AbstractBaseService<E, Long, R> {

    private final Class<E> entityClass;
    protected final ScoringConfig scoringConfig;

    public AbstractEquipmentService(R repository, Class<E> entityClass, ScoringConfig scoringConfig) {
        super(repository);
        this.entityClass = entityClass;
        this.scoringConfig = scoringConfig;
    }

    public abstract double calculateScore(E equipment, Album album);

    public boolean isSupport(EquipmentType equipmentType) {
        return entityClass.getSimpleName().equalsIgnoreCase(equipmentType.toString());
    }

    public List<E> findEquipmentByAlbumBudgetType(Album album, BigDecimal budget, EquipmentType type) {
        Specification<E> spec = Specification.allOf(
                EquipmentSpecification.isInStock(),
                EquipmentSpecification.priceLessThanAllBudget(budget),
                EquipmentSpecification.hasType(type)
        );
        return repository.findAll(spec)
                .stream()
                .sorted((e1, e2) -> Double.compare(calculateScore(e2, album), calculateScore(e1, album)))
                .limit(5)
                .toList();
    }

}
