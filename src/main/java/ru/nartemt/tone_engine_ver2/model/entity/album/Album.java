package ru.nartemt.tone_engine_ver2.model.entity.album;

import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.BodyShape;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

import jakarta.persistence.*;

@Entity
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

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 30)
    private Genre genre;

    @Column(name = "cover_url")
    private String coverUrl;

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

    public Album() { }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public Preset getPreset() {
        return preset;
    }

    public String getProTip() {
        return proTip;
    }

    public String getTargetBrand() {
        return targetBrand;
    }

    public BodyShape getTargetShape() {
        return targetShape;
    }

    public Genre getGenre() {
        return genre;
    }

    public boolean isLoFi() {
        return isLoFi;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setPreset(Preset preset) {
        this.preset = preset;
    }

    public void setProTip(String proTip) {
        this.proTip = proTip;
    }

    public void setTargetBrand(String targetBrand) {
        this.targetBrand = targetBrand;
    }

    public void setTargetShape(BodyShape targetShape) {
        this.targetShape = targetShape;
    }

    public void setLoFi(boolean loFi) {
        isLoFi = loFi;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
