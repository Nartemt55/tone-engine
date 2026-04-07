package ru.nartemt.tone_engine_ver2.model.entity.pedal;

import jakarta.persistence.*;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

@Entity
@Table(name = "pedals")
@PrimaryKeyJoinColumn(name = "id")
public class Pedal extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "effect_type", nullable = false)
    private EffectType effectType;

    @Column(name = "gain_level", nullable = false)
    private int gainLevel;

    @Column(name = "base_mid_responce")
    private int baseMidResponce;

    public Pedal() {
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public int getGainLevel() {
        return gainLevel;
    }

    public int getBaseMidResponce() {
        return baseMidResponce;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public void setGainLevel(int gainLevel) {
        this.gainLevel = gainLevel;
    }

    public void setBaseMidResponce(int baseMidResponce) {
        this.baseMidResponce = baseMidResponce;
    }
}
