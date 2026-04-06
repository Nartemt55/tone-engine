package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.util.List;

@Service
public class ToneAdvisorService {

    private final AlbumService albumService;

    @Autowired
    public ToneAdvisorService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public List<Album> getAllAlbums() {
        return albumService.findAll();
    }
}
