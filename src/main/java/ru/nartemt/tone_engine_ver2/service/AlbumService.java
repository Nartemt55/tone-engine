package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.preset.Preset;
import ru.nartemt.tone_engine_ver2.repository.AlbumRepository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.service.base.AbstractBaseService;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService extends AbstractBaseService<Album, Long, AlbumRepository> {
    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        super(albumRepository);
    }

    public Optional<Preset> getPresetByAlbumId(long id) {
        return repository.findById(id)
                .map(Album::getPreset);
    }
}
