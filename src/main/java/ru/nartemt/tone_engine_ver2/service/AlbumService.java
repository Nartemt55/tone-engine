package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.repository.AlbumRepository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album findById(long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public List<Album> findAll() {
        return albumRepository.findAll();
    }
}
