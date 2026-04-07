package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.repository.AmplifierRepository;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.amplifier.Amplifier;

import jakarta.transaction.Transactional;
import ru.nartemt.tone_engine_ver2.service.equipment.ProductProvider;

import java.util.List;

@Service
public class AmplifierService implements ProductProvider {

    private final AmplifierRepository amplifierRepository;

    @Autowired
    public AmplifierService(AmplifierRepository amplifierRepository) {
        this.amplifierRepository = amplifierRepository;
    }

    @Override
    @Transactional
    public List<Amplifier> getProducts(String sort) {
        if ("asc".equals(sort))
            return amplifierRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        return amplifierRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }

    @Override
    @Transactional
    public MusicalEquipment findById(long id) {
        return amplifierRepository.findById(id).orElse(null);
    }

    @Override
    public String supports() {
        return "amplifier";
    }
}
