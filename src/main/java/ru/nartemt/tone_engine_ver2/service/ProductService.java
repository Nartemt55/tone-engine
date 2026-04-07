package ru.nartemt.tone_engine_ver2.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.repository.EquipmentRepository;
import ru.nartemt.tone_engine_ver2.service.base.AbstractBaseService;
import ru.nartemt.tone_engine_ver2.service.equipment.ProductProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService extends AbstractBaseService<MusicalEquipment, Long, EquipmentRepository> {


    public ProductService(EquipmentRepository repository) {
        super(repository);
    }

    public MusicalEquipment getEquipmentById(long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<MusicalEquipment> getRequiredEquipment(String type, String sort) {
        // TODO : add specifications
        return repository.findAll();
    }
}
