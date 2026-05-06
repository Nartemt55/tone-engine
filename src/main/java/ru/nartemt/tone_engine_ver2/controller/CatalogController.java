package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CatalogController {

    private final EquipmentCatalogService catalogService;

    @Operation(
            summary = "Получить список оборудования",
            description = "По заданному filter (включающему тип оборудования и порядок сортировки)" +
                    " возвращает найденное, подходящее условиям оборудование"
    )
    @GetMapping
    public List<ProductShortDto> getProducts(CatalogFilterRequest filter) {
        return catalogService.getProductDtosByFilter(filter);
    }
}

