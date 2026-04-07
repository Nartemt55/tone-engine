package ru.nartemt.tone_engine_ver2.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

import java.math.BigDecimal;

public class EquipmentSpecification {

    public static <T extends MusicalEquipment> Specification<T> isInStock() {
        return (root, query, cb) ->
                cb.greaterThan(root.get("stock"), 0);
    }

    public static <T extends MusicalEquipment> Specification<T> priceLessThanAllBudget(BigDecimal budget) {
        if (budget == null)
            return null;
        return (root, query, cb) ->
                cb.lessThan(root.get("price"), budget);
    }
}
