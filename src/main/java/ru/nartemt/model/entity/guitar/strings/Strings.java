package ru.nartemt.model.entity.guitar.strings;

import ru.nartemt.model.entity.MusicalEquipment;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "strings")
public class Strings extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "gauge", nullable = false)
    private StringsGauge gauge;

    @Enumerated(EnumType.STRING)
    @Column(name = "strings_material", nullable = false)
    private StringsMaterial stringsMaterial;

    public Strings() {
    }

    public StringsGauge getGauge() {
        return gauge;
    }

    public StringsMaterial getStringsMaterial() {
        return stringsMaterial;
    }

    public void setGauge(StringsGauge gauge) {
        this.gauge = gauge;
    }

    public void setStringsMaterial(StringsMaterial stringsMaterial) {
        this.stringsMaterial = stringsMaterial;
    }
}
