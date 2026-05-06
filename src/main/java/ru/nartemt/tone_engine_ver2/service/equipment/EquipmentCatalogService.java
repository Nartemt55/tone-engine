package ru.nartemt.tone_engine_ver2.service.equipment;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.ProductShortMapper;
import ru.nartemt.tone_engine_ver2.model.dto.ProductShortDto;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.request.CatalogFilterRequest;
import ru.nartemt.tone_engine_ver2.repository.EquipmentRepository;
import ru.nartemt.tone_engine_ver2.repository.specification.EquipmentSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentCatalogService {

    private final EquipmentRepository<MusicalEquipment> equipmentRepository;
    private final ProductShortMapper mapper;

    private List<MusicalEquipment> findEquipmentByTypeAndSort(EquipmentType type, String sort) {
        Specification<MusicalEquipment> spec = Specification.allOf(
                EquipmentSpecification.orderBy(sort),
                EquipmentSpecification.hasType(type)
        );
        List<MusicalEquipment> equipmentList = equipmentRepository.findAll(spec);
        if (equipmentList.isEmpty())
            throw new EntityNotFoundException("Equipment not found");
        return equipmentList;
    }

    public Optional<MusicalEquipment> findById(Long id) {
        return equipmentRepository.findById(id);
    }

    public List<ProductShortDto> getProductDtosByFilter(CatalogFilterRequest filter) {
        return mapper.toDtoList(findEquipmentByTypeAndSort(filter.type(), filter.sort()));
    }
}
