package ru.nartemt.tone_engine_ver2.model.entity.amplifier;

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
@Table(name = "amplifiers")
@PrimaryKeyJoinColumn(name = "id")
public class Amplifier extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "amplifier_type", nullable = false)
    private AmplifierType amplifierType;

    @Column(name = "warmth_score", nullable = false)
    private int warmthScore;

    @Column(name = "output_power")
    private int outputPower;

}
