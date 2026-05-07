package ru.nartemt.tone_engine_ver2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nartemt.tone_engine_ver2.config.SecurityConfig;
import ru.nartemt.tone_engine_ver2.model.dto.CartDto;
import ru.nartemt.tone_engine_ver2.model.request.AddToCartRequest;
import ru.nartemt.tone_engine_ver2.security.CustomUserService;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;
import ru.nartemt.tone_engine_ver2.service.cart.CartService;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@Import(SecurityConfig.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockitoBean
    private JwtService jwtService;
    @MockitoBean
    private CustomUserService customUserService;
    @MockitoBean
    private CartService service;


    @Test
    void getCart_Unauthorized_ShouldReturn403() throws Exception {

        mockMvc.perform(get("/cart"))
                .andExpect(status().isForbidden());
        verifyNoInteractions(service);
    }

    @Test
    @WithMockUser
    void getCart_Success_ShouldReturnCartDto() throws Exception {
        CartDto expected = new CartDto(1L, null, null, new BigDecimal(1), 1);

        when(service.getCartDto()).thenReturn(expected);

        mockMvc.perform(get("/cart"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.id()))
                .andExpect(jsonPath("$.cartItems").value(expected.cartItems()))
                .andExpect(jsonPath("$.user").value(expected.user()))
                .andExpect(jsonPath("$.totalPrice").value(expected.totalPrice()))
                .andExpect(jsonPath("$.itemsAmount").value(expected.itemsAmount()));
        verify(service).getCartDto();
    }

    @Test
    @WithMockUser
    void addToCart_Success_ShouldReturnOk() throws Exception {
        AddToCartRequest request = new AddToCartRequest(1L, 1);
        mockMvc.perform(post("/cart/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath())
    }

}
