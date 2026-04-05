package ru.nartemt.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.model.entity.pedal.Pedal;

@Repository
public class PedalsDao extends EntityDao<Pedal> {

    public PedalsDao() {
        super(Pedal.class);
    }
}
