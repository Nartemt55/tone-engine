package ru.nartemt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.dao.GuitarsDao;
import ru.nartemt.model.entity.MusicalEquipment;
import ru.nartemt.model.entity.guitar.Guitar;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GuitarService implements ProductProvider {
    private final GuitarsDao guitarsDao;

    @Autowired
    public GuitarService(GuitarsDao guitarsDao) {
        this.guitarsDao = guitarsDao;
    }

    @Override
    @Transactional
    public List<Guitar> getProducts(String sort) {
        List<Guitar> guitars = guitarsDao.findAll();

        if (sort == null || sort.equals("desc")) {
            guitars.sort(MusicalEquipment.BY_PRICE_DESC);
            return guitars;
        }
        if (sort.equals("asc")) {
            guitars.sort(MusicalEquipment.BY_PRICE_ASC);
            return guitars;
        }
        return guitars;
    }

    @Override
    @Transactional
    public MusicalEquipment findById(long id) {
        return guitarsDao.findById(id);
    }

    @Override
    public String supports() {
        return "guitar";
    }

}
