package ru.nartemt.tone_engine_ver2.service.album;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
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

    public Optional<Album> findWithPresetsById(Long id) {
        if (id != null)
            return repository.findWithPresetsById(id);
        else
            throw new NullPointerException("Album id is null");
    }


    public List<Album> findAlbumsByGenre(Genre genre) {
        if (genre == null)
            return repository.findAll();
        return repository.findAllByGenre(genre);
    }
}
