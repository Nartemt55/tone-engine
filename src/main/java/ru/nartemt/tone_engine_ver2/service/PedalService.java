package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.repository.PedalRepository;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.pedal.Pedal;

import jakarta.transaction.Transactional;
import ru.nartemt.tone_engine_ver2.service.equipment.ProductProvider;

import java.util.List;

@Service
public class PedalService implements ProductProvider {

    private final PedalRepository pedalRepository;

    @Autowired
    public PedalService(PedalRepository pedalRepository) {
        this.pedalRepository = pedalRepository;
    }

    @Transactional
    @Override
    public List<Pedal> getProducts(String sort) {
        List<Pedal> pedals = pedalRepository.findAll();

        if (sort == null || sort.equals("desc")) {
            pedals.sort(MusicalEquipment.BY_PRICE_DESC);
            return pedals;
        }
        if (sort.equals("asc")) {
            pedals.sort(MusicalEquipment.BY_PRICE_ASC);
            return pedals;
        }
        return pedals;
    }

    @Override
    @Transactional
    public MusicalEquipment findById(long id) {
        return pedalRepository.findById(id).orElse(null);
    }

    @Override
    public String supports() {
        return "pedal";
    }

}
