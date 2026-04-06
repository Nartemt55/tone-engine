package ru.nartemt.tone_engine_ver2.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.pedal.Pedal;

@Repository
public class PedalsDao extends EntityDao<Pedal> {

    public PedalsDao() {
        super(Pedal.class);
    }
}
