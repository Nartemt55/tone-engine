package ru.nartemt.model.entity.amplifier;

import ru.nartemt.model.entity.MusicalEquipment;

import javax.persistence.*;


@Entity
@Table(name = "amplifiers")
@PrimaryKeyJoinColumn(name = "id")
public class Amplifier extends MusicalEquipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "amplifier_type", nullable = false)
    private AmplifierType amplifierType;

    @Column(name = "warmth_score", nullable = false)
    private int warmthScore;

    public Amplifier() {
    }

    public AmplifierType getAmplifierType() {
        return amplifierType;
    }

    public int getWarmthScore() {
        return warmthScore;
    }

    public void setAmplifierType(AmplifierType amplifierType) {
        this.amplifierType = amplifierType;
    }

    public void setWarmthScore(int warmthScore) {
        this.warmthScore = warmthScore;
    }
}
