package ru.nartemt.tone_engine_ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {
    List<Album> findAllByGenre(Genre genre);
    @Query("SELECT a FROM Album a JOIN FETCH a.preset WHERE a.id = :id")
    Optional<Album> findWithPresetsById(@Param("id") long id);
}
