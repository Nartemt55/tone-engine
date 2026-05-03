package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;

@Repository
public interface GuitarRepository extends EquipmentRepository<Guitar> {
}
