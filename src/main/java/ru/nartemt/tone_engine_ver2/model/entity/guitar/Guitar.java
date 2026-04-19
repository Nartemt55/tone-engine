package ru.nartemt.tone_engine_ver2.model.entity.guitar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.BodyShape;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.Tuning;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.strings.Strings;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guitars")
@PrimaryKeyJoinColumn(name = "id")
public class Guitar extends MusicalEquipment {

    @Column(name = "body_shape", nullable = false)
    @Enumerated(EnumType.STRING)
    private BodyShape bodyShape;

    @Column(name = "body_wood", length = 20)
    private String bodyWood;

    @Column(name = "pickup_config")
    @Enumerated(EnumType.STRING)
    private PickupConfig pickupConfig;

    @Column(name = "scale_length")
    private double scaleLength;

    @ManyToOne
    @JoinColumn(name = "strings_id")
    private Strings strings;

    @Column(name = "tuning", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tuning tuning;

    @Column(name = "base_treble_response")
    private int baseTrebleResponse;

    @Column(name = "output_power")
    private int outputPower;

    public String getTensionInfo() {
        int tensionIndex = this.tuning.getPitchOffset() - this.strings.getGauge().getTensionIndex();

        return switch (tensionIndex) {
            case 0  -> "Perfect combination! Оптимальное натяжение для этой мензуры.";
            case 1  -> "Slightly loose. Мягкое натяжение, удобно для бендов, но возможен лязг.";
            case 2  -> "Loose strings. Струны заметно провисают, четкость звука теряется.";
            case -1 -> "Firm tension. Натяжение выше среднего, звук плотнее, играть тяжелее.";
            case -2 -> "Tight strings. Очень жестко. Повышенный износ ладов и риск обрыва.";
            default -> (tensionIndex > 0)
                    ? "Flabby strings! Струны слишком расслаблены, играть невозможно."
                    : "Danger! Критическое натяжение. Риск деформации грифа!";
        };
    }
}
