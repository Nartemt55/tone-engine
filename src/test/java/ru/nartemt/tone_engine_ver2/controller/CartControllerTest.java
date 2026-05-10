package ru.nartemt.tone_engine_ver2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    void addToCart_Success_ShouldReturn201() throws Exception {
        AddToCartRequest request = new AddToCartRequest(1L, 1);
        mockMvc.perform(post("/cart/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void addToCart_IdNotFound_ShouldReturn422() throws Exception {
        AddToCartRequest request = new AddToCartRequest(1L, 1);

        doThrow(new EntityNotFoundException("Equipment with " + request.id() + " not found"))
                .when(service)
                .addToCart(request);

        mockMvc.perform(post("/cart/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").value("Equipment with " + request.id() + " not found"));
        verify(service).addToCart(any(AddToCartRequest.class));
    }

    @Test
    @WithMockUser
    void removeFromCart_Success_ShouldReturn201() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/cart/items/{id}", id)
                        .with(csrf()))
                .andExpect(status().isNoContent());
        verify(service).removeFromCart(id);
    }

    @Test
    @WithMockUser
    void removeFromCart_IdNotFound_ShouldReturn403() throws Exception {
        Long id = 1L;

        doThrow(new AccessDeniedException("You must login before get a cart!"))
                .when(service)
                .removeFromCart(id);

        mockMvc.perform(delete("/cart/items/{id}", id)
                        .with(csrf()))
                .andExpect(status().isForbidden());
        verify(service).removeFromCart(id);
    }


    @Test
    @WithMockUser
    void handleClearCart_Success_ShouldReturn201() throws Exception {

        mockMvc.perform(delete("/cart/items")
                        .with(csrf()))
                .andExpect(status().isNoContent());
        verify(service).clearCart();
    }

    @Test
    @WithMockUser
    void handleClearCart_IdNotFound_ShouldReturn403() throws Exception {

        doThrow(new AccessDeniedException("You must login before get a cart!"))
                .when(service)
                .clearCart();

        mockMvc.perform(delete("/cart/items")
                        .with(csrf()))
                .andExpect(status().isForbidden());
        verify(service).clearCart();
    }

}
