package ru.nartemt.tone_engine_ver2.dao;

import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

@Repository
public class AlbumsDao extends EntityDao<Album> {

    public AlbumsDao() {
        super(Album.class);
    }
}
