package ru.nartemt.tone_engine_ver2.service.equipment;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.repository.EquipmentRepository;
import ru.nartemt.tone_engine_ver2.repository.specification.EquipmentSpecification;

import java.util.List;

@Service
public class EquipmentCatalogService {

    private final EquipmentRepository equipmentRepository;
    @Autowired
    public EquipmentCatalogService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<MusicalEquipment> findEquipmentByTypeAndSort(String type, String sort) {
        Specification<MusicalEquipment> spec = Specification.allOf(EquipmentSpecification.orderBy(sort));
        return equipmentRepository.findAll(spec)
                .stream()
                .filter(e -> e.getEquipmentType().toString().equalsIgnoreCase(type))
                .toList();
    }

    public MusicalEquipment findById(Long id) {
        if (id != null)
            return equipmentRepository.findById(id).orElseThrow();
        else
            throw new NullPointerException();
    }
}
