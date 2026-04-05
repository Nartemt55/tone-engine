package ru.nartemt.service;

import ru.nartemt.model.entity.MusicalEquipment;

import java.util.List;

public interface ProductProvider {

    List<? extends MusicalEquipment> getProducts(String sort);
    MusicalEquipment findById(long id);
    String supports();
}
