package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

public interface EquipmentRepository<T extends MusicalEquipment> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {
}
