package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nartemt.tone_engine_ver2.model.dto.AdvisorResponseDto;
import ru.nartemt.tone_engine_ver2.model.dto.AlbumPreviewDto;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.service.album.AlbumService;
import ru.nartemt.tone_engine_ver2.service.tone.ToneAdvisorService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final ToneAdvisorService toneAdvisorService;
    private final AlbumService albumService;

    @Autowired
    public AlbumController(ToneAdvisorService toneAdvisorService, AlbumService albumService) {
        this.toneAdvisorService = toneAdvisorService;
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumPreviewDto> getAlbums(@RequestParam(required = false) Genre genre) {
        return albumService.findAlbumDtosByGenre(genre);
    }

    @GetMapping("/{id}/products")
    public AdvisorResponseDto getAdvisorResponse(
            @PathVariable Long id,
            @RequestParam(required = false) BigDecimal budget) {

        return toneAdvisorService.getAdvisorResponseDto(id, budget);
    }
}
