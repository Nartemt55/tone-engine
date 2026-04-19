package ru.nartemt.tone_engine_ver2.service.equipment;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class EquipmentCatalogService {

    private final EquipmentRepository<MusicalEquipment> equipmentRepository;
    private final ProductShortMapper mapper;
    @Autowired
    public EquipmentCatalogService(EquipmentRepository<MusicalEquipment> equipmentRepository, ProductShortMapper mapper) {
        this.equipmentRepository = equipmentRepository;
        this.mapper = mapper;
    }

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

    public MusicalEquipment findById(Long id) {
        if (id != null)
            return equipmentRepository.findById(id).orElseThrow();
        else
            throw new NullPointerException();
    }

    public List<ProductShortDto> getProductDtosByFilter(CatalogFilterRequest filter) {
        return mapper.toDtoList(findEquipmentByTypeAndSort(filter.type(), filter.sort()));
    }
}
