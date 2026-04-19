package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nartemt.tone_engine_ver2.model.dto.ProductShortDto;
import ru.nartemt.tone_engine_ver2.model.request.CatalogFilterRequest;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "CatalogController")
public class CatalogController {

    private final EquipmentCatalogService catalogService;

    @Autowired
    public CatalogController(EquipmentCatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<ProductShortDto> getProducts(CatalogFilterRequest filter) {
        return catalogService.getProductDtosByFilter(filter);
    }
}

