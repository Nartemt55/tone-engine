package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.service.album.AlbumService;
import ru.nartemt.tone_engine_ver2.service.tone.ToneAdvisorService;

import java.math.BigDecimal;

@Controller
public class ToneAdvisorController {

    private final ToneAdvisorService toneAdvisorService;
    private final AlbumService albumService;

    @Autowired
    public ToneAdvisorController(ToneAdvisorService toneAdvisorService, AlbumService albumService) {
        this.toneAdvisorService = toneAdvisorService;
        this.albumService = albumService;
    }

    @GetMapping("/tone-advisor")
    public String getToneAdvisorPage(Model model,
                                     @RequestParam(required = false) Genre genre,
                                     @RequestParam(required = false, defaultValue = "1350") BigDecimal budget,
                                     @RequestParam(required = false) Long albumId) {

        if (albumId != null)
            model.addAttribute("advisorResponse", toneAdvisorService.getAdvisorResponseDto(albumId, budget));
        model.addAttribute("genres", Genre.values());
        model.addAttribute("albums", albumService.findAll());

        return "tone-advisor";
    }
}
