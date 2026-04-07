package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.repository.GuitarRepository;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;

import jakarta.transaction.Transactional;
import ru.nartemt.tone_engine_ver2.service.equipment.ProductProvider;

import java.util.List;

@Service
public class GuitarService implements ProductProvider {
    private final GuitarRepository guitarRepository;

    @Autowired
    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    @Override
    @Transactional
    public List<Guitar> findAllSorted(String sort) {
        if ("asc".equals(sort))
            return guitarRepository.findAll(Sort.by(Sort.Direction.ASC));
        return guitarRepository.findAll(Sort.by(Sort.Direction.DESC));
    }

    @Override
    @Transactional
    public MusicalEquipment findById(long id) {
        return guitarRepository.findById(id).orElse(null);
    }

    @Override
    public String supports() {
        return "guitar";
    }

}
