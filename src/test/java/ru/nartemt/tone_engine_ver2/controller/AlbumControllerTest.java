package ru.nartemt.tone_engine_ver2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nartemt.tone_engine_ver2.config.SecurityConfig;
import ru.nartemt.tone_engine_ver2.model.dto.AdvisorResponseDto;
import ru.nartemt.tone_engine_ver2.model.dto.AlbumPreviewDto;
import ru.nartemt.tone_engine_ver2.model.entity.album.Album;
import ru.nartemt.tone_engine_ver2.model.entity.album.Genre;
import ru.nartemt.tone_engine_ver2.security.CustomUserService;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;
import ru.nartemt.tone_engine_ver2.service.album.AlbumService;
import ru.nartemt.tone_engine_ver2.service.tone.ToneAdvisorService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AlbumController.class)
@Import(SecurityConfig.class)
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserService customUserService;

    @MockitoBean
    private ToneAdvisorService toneAdvisorService;

    @MockitoBean
    private AlbumService albumService;

    @Test
    @WithMockUser
    void getAlbums_Success_ShouldReturn201() throws Exception {
        List<AlbumPreviewDto> expected = List.of(new AlbumPreviewDto(1L, "some_title",
                "some_artist", "some_url"));

        when(albumService.findAlbumDtosByGenre(any(Genre.class))).thenReturn(expected);

        mockMvc.perform(get("/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("genre", Genre.BLACK_METAL.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getFirst().id()))
                .andExpect(jsonPath("$[0].title").value(expected.getFirst().title()))
                .andExpect(jsonPath("$[0].artist").value(expected.getFirst().artist()))
                .andExpect(jsonPath("$[0].coverUrl").value(expected.getFirst().coverUrl()));
        verify(albumService).findAlbumDtosByGenre(any(Genre.class));
    }

    @Test
    @WithMockUser
    void getAdvisorResponse_Success_ShouldReturn200() throws Exception {
        Long id = 1L;
        BigDecimal budget = new BigDecimal(1);
        AdvisorResponseDto expected = new AdvisorResponseDto(
                new Album(),
                List.of(),
                List.of(),
                new BigDecimal(1)
        );
        when(toneAdvisorService.getAdvisorResponseDto(anyLong(), any(BigDecimal.class))).thenReturn(expected);

        mockMvc.perform(get("/albums/{id}/products", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("budget", budget.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentList").isArray())
                .andExpect(jsonPath("$.frequencies").isArray())
                .andExpect(jsonPath("$.totalPrice").value(budget));
        verify(toneAdvisorService).getAdvisorResponseDto(anyLong(), any(BigDecimal.class));
    }
}
