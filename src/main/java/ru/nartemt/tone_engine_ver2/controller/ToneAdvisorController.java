package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.tone_engine_ver2.model.entity.MusicalEquipment;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.service.ProductService;
import ru.nartemt.tone_engine_ver2.service.ToneAdvisorService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ToneAdvisorController {

    private final ToneAdvisorService toneAdvisorService;

    @Autowired
    public ToneAdvisorController(ToneAdvisorService toneAdvisorService) {
        this.toneAdvisorService = toneAdvisorService;
    }

    @GetMapping("/tone-advisor")
    public String getToneAdvisorPage(Model model,
                                     @RequestParam(required = false) String genre,
                                     @RequestParam(required = false) Integer budget,
                                     @RequestParam(required = false) Long albumId) {

        List<Album> albums = toneAdvisorService.getAllAlbums();

        model.addAttribute("genres", Genre.values());
        model.addAttribute("albums", albums);
        model.addAttribute("selectedAlbum", null);
        return "tone-advisor";
    }
}
