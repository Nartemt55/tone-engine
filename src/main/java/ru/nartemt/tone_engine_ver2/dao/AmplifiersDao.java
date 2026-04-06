package ru.nartemt.tone_engine_ver2.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.amplifier.Amplifier;

@Repository
public class AmplifiersDao extends EntityDao<Amplifier> {

    public AmplifiersDao() {
        super(Amplifier.class);
    }
}
