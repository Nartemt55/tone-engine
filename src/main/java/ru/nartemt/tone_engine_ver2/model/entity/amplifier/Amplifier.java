package ru.nartemt.tone_engine_ver2.model.entity.amplifier;

import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;

import jakarta.persistence.*;


@Entity
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

    public Amplifier() {
    }

    public AmplifierType getAmplifierType() {
        return amplifierType;
    }

    public int getWarmthScore() {
        return warmthScore;
    }

    public int getOutputPower() {
        return outputPower;
    }

    public void setAmplifierType(AmplifierType amplifierType) {
        this.amplifierType = amplifierType;
    }

    public void setWarmthScore(int warmthScore) {
        this.warmthScore = warmthScore;
    }

    public void setOutputPower(int outputPower) {
        this.outputPower = outputPower;
    }
}
