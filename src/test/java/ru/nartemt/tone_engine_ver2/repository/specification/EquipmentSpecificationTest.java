package ru.nartemt.tone_engine_ver2.repository.specification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.Guitar;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.BodyShape;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.Tuning;
import ru.nartemt.tone_engine_ver2.repository.GuitarRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EquipmentSpecificationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GuitarRepository repository;

    @Test
    void shouldFilterEquipmentThatIsInStockByPrice() {

        Guitar match = createBaseGuitar("Ibanez", new BigDecimal("100"), 10);
        match.setBodyShape(BodyShape.SUPERSTRAT);
        match.setTuning(Tuning.B_STANDARD);
        match.setEquipmentType(EquipmentType.GUITAR);

        Guitar outOfStock = createBaseGuitar("Fender", new BigDecimal("100"), 0);
        outOfStock.setBodyShape(BodyShape.STRATOCASTER);
        outOfStock.setTuning(Tuning.C_SHARP_STANDARD);
        outOfStock.setEquipmentType(EquipmentType.GUITAR);

        entityManager.persist(match);
        entityManager.persist(outOfStock);
        entityManager.flush();
        entityManager.clear();

        Specification<Guitar> spec = EquipmentSpecification
                .<Guitar>isInStock()
                .and(EquipmentSpecification.hasType(EquipmentType.GUITAR))
                .and(EquipmentSpecification.priceLessThanAllBudget(new BigDecimal("1000")));

        List<Guitar> result = repository.findAll(spec);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBrand()).isEqualTo("Ibanez");
    }

    private Guitar createBaseGuitar(String brand, BigDecimal price, int stock) {
        Guitar g = new Guitar();
        g.setBrand(brand);
        g.setPrice(price);
        g.setStock(stock);
        return g;
    }

}
