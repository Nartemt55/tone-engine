package ru.nartemt.tone_engine_ver2.service;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

import java.util.List;

public interface ProductProvider {

    List<? extends MusicalEquipment> getProducts(String sort);
    MusicalEquipment findById(long id);
    String supports();
}
