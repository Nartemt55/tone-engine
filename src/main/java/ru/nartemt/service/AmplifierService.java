package ru.nartemt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.dao.AmplifiersDao;
import ru.nartemt.model.entity.MusicalEquipment;
import ru.nartemt.model.entity.amplifier.Amplifier;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AmplifierService implements ProductProvider{

    private final AmplifiersDao amplifiersDao;

    @Autowired
    public AmplifierService(AmplifiersDao amplifiersDao) {
        this.amplifiersDao = amplifiersDao;
    }

    @Override
    @Transactional
    public List<Amplifier> getProducts(String sort) {
        List<Amplifier> amps = amplifiersDao.findAll();

        if (sort == null || sort.equals("desc")) {
            amps.sort(MusicalEquipment.BY_PRICE_DESC);
            return amps;
        }
        if (sort.equals("asc")) {
            amps.sort(MusicalEquipment.BY_PRICE_ASC);
            return amps;
        }
        return amps;
    }

    @Override
    @Transactional
    public MusicalEquipment findById(long id) {
        return amplifiersDao.findById(id);
    }

    @Override
    public String supports() {
        return "amplifier";
    }
}
