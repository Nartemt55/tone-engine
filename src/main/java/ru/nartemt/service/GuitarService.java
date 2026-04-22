package ru.nartemt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.dao.GuitarsDao;
import ru.nartemt.model.entity.guitar.Guitar;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GuitarService {
    private final GuitarsDao guitarsDao;

    @Autowired
    public GuitarService(GuitarsDao guitarsDao) {
        this.guitarsDao = guitarsDao;
    }

    @Transactional
    public List<Guitar> findAll() {
        return guitarsDao.findAll();
    }

    public String supports() {
        return "guitar";
    }

}
