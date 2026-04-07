package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.pedal.Pedal;

@Repository
public interface PedalRepository extends JpaRepository<Pedal, Long>, JpaSpecificationExecutor<Pedal> {
    
}
