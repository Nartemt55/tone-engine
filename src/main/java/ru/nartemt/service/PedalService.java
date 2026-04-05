package ru.nartemt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.dao.PedalsDao;
import ru.nartemt.model.entity.MusicalEquipment;
import ru.nartemt.model.entity.pedal.Pedal;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PedalService implements ProductProvider {

    private final PedalsDao pedalsDao;

    @Autowired
    public PedalService(PedalsDao pedalsDao) {
        this.pedalsDao = pedalsDao;
    }

    @Transactional
    @Override
    public List<Pedal> getProducts(String sort) {
        List<Pedal> pedals = pedalsDao.findAll();

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
        return pedalsDao.findById(id);
    }

    @Override
    public String supports() {
        return "pedal";
    }

}
