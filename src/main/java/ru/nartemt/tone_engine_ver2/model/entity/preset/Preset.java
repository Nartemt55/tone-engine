package ru.nartemt.tone_engine_ver2.model.entity.preset;

import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.Tuning;

import jakarta.persistence.*;

@Entity
@Table(name = "sound_presets")
public class Preset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_pickup", nullable = false)
    private PickupConfig reqPickup;

    @Column(name = "freq_low", nullable = false) // 60-150Hz
    private int freqLow;

    @Column(name = "freq_mid", nullable = false) // 400-1kHz
    private int freqMid;

    @Column(name = "freq_high", nullable = false) // 2.4k-15kHz
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

    public long getId() {
        return id;
    }

    public PickupConfig getReqPickup() {
        return reqPickup;
    }

    public int getFreqLow() {
        return freqLow;
    }

    public int getFreqMid() {
        return freqMid;
    }

    public int getFreqHigh() {
        return freqHigh;
    }

    public Tuning getReqTuning() {
        return reqTuning;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setReqPickup(PickupConfig reqPickup) {
        this.reqPickup = reqPickup;
    }

    public void setFreqLow(int freqLow) {
        this.freqLow = freqLow;
    }

    public void setFreqMid(int freqMid) {
        this.freqMid = freqMid;
    }

    public void setFreqHigh(int freqHigh) {
        this.freqHigh = freqHigh;
    }

    public void setReqTuning(Tuning reqTuning) {
        this.reqTuning = reqTuning;
    }

}
