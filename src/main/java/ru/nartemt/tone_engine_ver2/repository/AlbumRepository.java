package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
