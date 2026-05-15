package ru.nartemt.tone_engine_ver2.service.album;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import ru.nartemt.tone_engine_ver2.mapper.AlbumPreviewMapper;
import ru.nartemt.tone_engine_ver2.model.dto.AlbumPreviewDto;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {
    @Mock
    private AlbumPreviewMapper mapper;
    @Mock
    private AlbumRepository repository;

    @InjectMocks
    private AlbumService service;

    @Test
    void findWithPresetById_NullId_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            service.findWithPresetsById(null);
        });
    }

    @Test
    void findWithPresetById_CorrectId_Successfully() {
        Album album = new Album();

        when(repository.findWithPresetsById(anyLong())).thenReturn(Optional.of(album));

        var result = service.findWithPresetsById(1L);

        assertTrue(result.isPresent());
        assertEquals(result.get(), album);
    }

    @Test
    void findAlbumsByGenre_NullId_ReturnsAllAlbums() {
        when(repository.findAll()).thenReturn(List.of(new Album(), new Album()));

        var result = service.findAlbumsByGenre(null);

        assertEquals(2, result.size());
        verify(repository).findAll();
        verify(repository, never()).findAllByGenre(any());
    }

    @Test
    void findAlbumsByGenre_CorrectId_Successfully() {
        Genre expectedGenre = Genre.BLACK_METAL;
        when(repository.findAllByGenre(expectedGenre)).thenReturn(List.of(new Album()));

        var result = service.findAlbumsByGenre(expectedGenre);

        assertEquals(1, result.size());
        verify(repository).findAllByGenre(expectedGenre);
        verify(repository, never()).findAll();
    }

    @Test
    void findAlbumDtosByGenre_NullGenre_ReturnsAllAlbumDtos() {
        List<Album> allAlbums = List.of(new Album());
        List<AlbumPreviewDto> allPreviews = List.of(new AlbumPreviewDto(1L, "title", "artist", "url"));

        when(mapper.toDtoList(allAlbums)).thenReturn(allPreviews);
        when(repository.findAll()).thenReturn(allAlbums);

        var result = service.findAlbumDtosByGenre(null);

        assertEquals(allPreviews, result);
        verify(repository).findAll();
    }

    @Test
    void findAlbumDtosByGenre_CorrectGenre_ReturnsSpecifiedDtos() {
        List<Album> albums = List.of(new Album(), new Album());
        List<AlbumPreviewDto> allPreviews = List.of(
                new AlbumPreviewDto(1L, "title", "artist", "url"),
                new AlbumPreviewDto(2L, "title2", "artist2", "url2")
                );
        Genre genre = Genre.BLACK_METAL;

        when(repository.findAllByGenre(genre)).thenReturn(albums);
        when(mapper.toDtoList(albums)).thenReturn(allPreviews);

        var result = service.findAlbumDtosByGenre(genre);

        assertEquals(allPreviews, result);
        verify(repository).findAllByGenre(genre);
        verify(repository, never()).findAll();
    }
}
