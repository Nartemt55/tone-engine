package ru.nartemt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.model.entity.MusicalEquipment;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<ProductProvider> providers;


    @Autowired
    public ProductService(List<ProductProvider> providers) {
        this.providers = providers;
    }

    public MusicalEquipment getEquipmentById(long id) {
        for (ProductProvider provider : providers) {
            MusicalEquipment equipment = provider.findById(id);
            if (equipment != null)
                return equipment;
        }
        return null;
    }

    public List<MusicalEquipment> getRequiredEquipment(String type, String sort) {
        List<MusicalEquipment> requiredEquipment = new ArrayList<>();
        for (ProductProvider provider : providers) {
            if (type == null || type.isBlank()|| provider.supports().equals(type))
                requiredEquipment.addAll(provider.getProducts(sort));
        }

        return requiredEquipment;
    }
}
