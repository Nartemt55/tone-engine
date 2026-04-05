package ru.nartemt.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.model.entity.amplifier.Amplifier;

@Repository
public class AmplifiersDao extends EntityDao<Amplifier>{

    public AmplifiersDao() {
        super(Amplifier.class);
    }
}
