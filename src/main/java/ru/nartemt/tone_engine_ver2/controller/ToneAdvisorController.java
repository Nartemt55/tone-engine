package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.tone_engine_ver2.model.dto.AdvisorResponceDto;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.service.AlbumService;
import ru.nartemt.tone_engine_ver2.service.ProductService;
import ru.nartemt.tone_engine_ver2.service.ToneAdvisorService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
                                     @RequestParam(required = false) Integer budget,
                                     @RequestParam(required = false) BigDecimal albumId) {


        if (albumId != null) {
            AdvisorResponceDto dto = toneAdvisorService.getRecommendations(budget, albumId);
            model.addAttribute("selectedAlbum", dto);
        }

        model.addAttribute("genres", Genre.values());
        model.addAttribute("albums", albumService.findAll());

        return "tone-advisor";
    }
}
