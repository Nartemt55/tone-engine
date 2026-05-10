package ru.nartemt.tone_engine_ver2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nartemt.tone_engine_ver2.config.SecurityConfig;
import ru.nartemt.tone_engine_ver2.model.dto.ProductShortDto;
import ru.nartemt.tone_engine_ver2.model.entity.EquipmentType;
import ru.nartemt.tone_engine_ver2.model.request.AddToCartRequest;
import ru.nartemt.tone_engine_ver2.model.request.CatalogFilterRequest;
import ru.nartemt.tone_engine_ver2.security.CustomUserService;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;
import ru.nartemt.tone_engine_ver2.service.equipment.EquipmentCatalogService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogController.class)
@Import(SecurityConfig.class)
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private EquipmentCatalogService service;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserService customUserService;

    @Test
    @WithMockUser
    void getProducts_Success_ShouldReturn200() throws Exception {
        List<ProductShortDto> expected = List.of(new ProductShortDto(1L, "some_brand", "some_model",
                "some_url", new BigDecimal(1)));

        when(service.getProductDtosByFilter(any(CatalogFilterRequest.class)))
                .thenReturn(expected);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(any(CatalogFilterRequest.class))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().id()))
                .andExpect(jsonPath("$[0].brand").value(expected.getFirst().brand()))
                .andExpect(jsonPath("$[0].model").value(expected.getFirst().model()))
                .andExpect(jsonPath("$[0].imageUrl").value(expected.getFirst().imageUrl()))
                .andExpect(jsonPath("$[0].price").value(expected.getFirst().price()));
        verify(service).getProductDtosByFilter(any(CatalogFilterRequest.class));

    }

    @Test
    @WithMockUser
    void getProducts_WrongId_ShouldReturn422() throws Exception {
       when(service.getProductDtosByFilter(any(CatalogFilterRequest.class)))
               .thenThrow(new EntityNotFoundException("Equipment not found"));

       mockMvc.perform(get("/products")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(mapper.writeValueAsString(any(CatalogFilterRequest.class))))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.status").value(422))
               .andExpect(jsonPath("$.message").value("Equipment not found"));
        verify(service).getProductDtosByFilter(any(CatalogFilterRequest.class));
    }

}
