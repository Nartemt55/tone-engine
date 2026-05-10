package ru.nartemt.tone_engine_ver2.model.entity.preset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.Tuning;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}
