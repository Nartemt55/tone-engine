package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;

public interface EquipmentRepository extends JpaRepository<MusicalEquipment, Long>,
        JpaSpecificationExecutor<MusicalEquipment> {
}
