package ru.nartemt.tone_engine_ver2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.tone_engine_ver2.model.dto.ProductShortDto;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

import java.util.List;

@Controller
public class HomeController {

    private final EquipmentCatalogService catalogService;

    @Autowired
    public HomeController(EquipmentCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model,
                              @RequestParam(required = false) EquipmentType type,
                              @RequestParam(required = false) String sort) {

        List<ProductShortDto> products = catalogService.getProductDtosByTypeAndSort(type, sort);
        model.addAttribute("products", products);
        return "index";
    }
}

