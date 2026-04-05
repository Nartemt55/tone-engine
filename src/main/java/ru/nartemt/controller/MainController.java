package ru.nartemt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nartemt.model.entity.MusicalEquipment;
import ru.nartemt.service.ProductService;

import java.util.List;

@Controller
public class MainController {

    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String getMainPage(Model model,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false) String sort) {

        List<MusicalEquipment> products = productService.getRequiredEquipment(type, sort);
        model.addAttribute("gearList", products);
        return "index";
    }
}
