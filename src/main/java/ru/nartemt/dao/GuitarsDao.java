package ru.nartemt.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.model.entity.guitar.Guitar;

@Repository
public class GuitarsDao extends EntityDao<Guitar> {

    public GuitarsDao() {
        super(Guitar.class);
    }
}
