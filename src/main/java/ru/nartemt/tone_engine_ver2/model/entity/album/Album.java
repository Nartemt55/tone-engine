package ru.nartemt.tone_engine_ver2.model.entity.album;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.BodyShape;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false, length = 30)
    private String title;

    @Column(name = "artist", nullable = false, length = 30)
    private String artist;

    @Column(name = "cover_url")
    private String coverUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30)
    private Genre genre;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preset_id", nullable = false)
    private Preset preset;

    @Column(name = "pro_tip")
    private String proTip;

    @Column(name = "target_brand")
    private String targetBrand;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_shape")
    private BodyShape targetShape;

    @Column(name = "is_lo_fi")
    private boolean isLoFi;

}
