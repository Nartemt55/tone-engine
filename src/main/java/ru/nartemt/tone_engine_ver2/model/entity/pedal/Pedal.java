package ru.nartemt.tone_engine_ver2.model.entity.pedal;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedals")
@PrimaryKeyJoinColumn(name = "id")
public class Pedal extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "effect_type", nullable = false)
    private EffectType effectType;

    @Column(name = "gain_level", nullable = false)
    private int gainLevel;

    @Column(name = "base_mid_response")
    private int baseMidResponse;

}
