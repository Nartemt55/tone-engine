package ru.nartemt.model.entity.preset;

import ru.nartemt.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.model.entity.guitar.settings.Tuning;

import javax.persistence.*;

@Entity
@Table(name = "sound_presets")
public class Preset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_pickup", nullable = false)
    private PickupConfig reqPickup;

    @Column(name = "freq_low") // 60-150Hz
    private int freqLow;

    @Column(name = "freq_mid") // 400-1kHz
    private int freqMid;

    @Column(name = "freq_high") // 2.4k-15kHz
    private int freqHigh;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_tuning")
    private Tuning reqTuning;

    @Column(name = "min_gain_required", nullable = false)
    private int minGainRequired;

    @Column(name = "required_output_power", nullable = false)
    private int requiredOutputPower;

    @Column(name = "warmth_target", nullable = false)
    private int warmthTarget;



    public Preset() { }

    public int getMinGainRequired() {
        return minGainRequired;
    }

    public int getRequiredOutputPower() {
        return requiredOutputPower;
    }

    public int getWarmthTarget() {
        return warmthTarget;
    }

    public void setMinGainRequired(int minGainRequired) {
        this.minGainRequired = minGainRequired;
    }

    public void setRequiredOutputPower(int requiredOutputPower) {
        this.requiredOutputPower = requiredOutputPower;
    }

    public void setWarmthTarget(int warmthTarget) {
        this.warmthTarget = warmthTarget;
    }
}
