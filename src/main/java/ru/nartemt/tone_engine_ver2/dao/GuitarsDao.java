package ru.nartemt.tone_engine_ver2.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;

@Repository
public class GuitarsDao extends EntityDao<Guitar> {

    public GuitarsDao() {
        super(Guitar.class);
    }
}
