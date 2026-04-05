package ru.nartemt.model.entity.pedal;

import ru.nartemt.model.entity.MusicalEquipment;

import javax.persistence.*;

@Entity
@Table(name = "pedals")
@PrimaryKeyJoinColumn(name = "id")
public class Pedal extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "effect_type", nullable = false)
    private EffectType effectType;

    @Column(name = "gain_level", nullable = false)
    private int gainLevel;

    public Pedal() {
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public int getGainLevel() {
        return gainLevel;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public void setGainLevel(int gainLevel) {
        this.gainLevel = gainLevel;
    }
}
