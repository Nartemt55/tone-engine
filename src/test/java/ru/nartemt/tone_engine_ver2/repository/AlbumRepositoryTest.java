package ru.nartemt.tone_engine_ver2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.guitar.settings.PickupConfig;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AlbumRepository repository;

    @Test
    void findWithPresetsById_ShouldFetchPresets() {
        Preset preset = createTestPreset();
        Album album = createTestAlbum();
        album.setPreset(preset);

        entityManager.persist(preset);
        entityManager.persist(album);

        Optional<Album> result = repository.findWithPresetsById(album.getId());

        assertThat(result).isPresent();
        assertThat(result.get().getPreset().getWarmthTarget()).isEqualTo(preset.getWarmthTarget());
    }

    private Preset createTestPreset() {
        Preset p = new Preset();
        p.setFreqLow(1);
        p.setWarmthTarget(1);
        p.setFreqHigh(1);
        p.setFreqMid(1);
        p.setMinGainRequired(1);
        p.setRequiredOutputPower(1);
        p.setReqPickup(PickupConfig.SS);
        return p;
    }

    private Album createTestAlbum() {
        Album a = new Album();
        a.setArtist("Linkin Park");
        a.setTitle("Hybrid Theory");
        return a;
    }

}
