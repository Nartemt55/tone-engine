package ru.nartemt.tone_engine_ver2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.dao.AlbumsDao;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumsDao albumsDao;

    @Autowired
    public AlbumService(AlbumsDao albumsDao) {
        this.albumsDao = albumsDao;
    }

    public Album findById(long id) {
        return albumsDao.findById(id);
    }

    public List<Album> findAll() {
        return albumsDao.findAll();
    }
}
