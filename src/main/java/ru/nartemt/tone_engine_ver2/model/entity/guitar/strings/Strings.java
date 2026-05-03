package ru.nartemt.tone_engine_ver2.model.entity.guitar.strings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "strings")
public class Strings extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "gauge", nullable = false)
    private StringsGauge gauge;

    @Enumerated(EnumType.STRING)
    @Column(name = "strings_material", nullable = false)
    private StringsMaterial stringsMaterial;

}
