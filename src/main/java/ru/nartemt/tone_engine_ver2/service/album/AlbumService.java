package ru.nartemt.tone_engine_ver2.service.album;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.AlbumPreviewMapper;
import ru.nartemt.tone_engine_ver2.model.dto.AlbumPreviewDto;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.repository.AlbumRepository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.service.base.AbstractBaseService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AlbumService extends AbstractBaseService<Album, Long, AlbumRepository> {

    private final AlbumPreviewMapper mapper;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, AlbumPreviewMapper mapper) {
        super(albumRepository);
        this.mapper = mapper;
    }

    public Optional<Album> findWithPresetsById(Long id) {
        if (id != null)
            return repository.findWithPresetsById(id);
        else
            throw new NullPointerException("Album id is null");
    }

    public List<Album> findAlbumsByGenre(Genre genre) {
        if (genre == null)
            return repository.findAll();
        log.warn("Genre is null. Return every album");
        return repository.findAllByGenre(genre);
    }

    public List<AlbumPreviewDto> findAlbumDtosByGenre(Genre genre) {
        return mapper.toDtoList(findAlbumsByGenre(genre));
    }
}
