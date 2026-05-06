package ru.nartemt.tone_engine_ver2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserCredentialsDto;
import ru.nartemt.tone_engine_ver2.security.CustomUserService;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;
import ru.nartemt.tone_engine_ver2.service.user.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AuthController.class)
@WithMockUser
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserService customUserService;

    @Test
    void signIn_ShouldReturnToken_WhenValidCredentials() throws Exception {

        UserCredentialsDto credentials = new UserCredentialsDto("admin", "admin");
        JwtAuthenticationDto expectedToken = new JwtAuthenticationDto("some.auth.token", "some.refresh.token");

        when(userService.signIn(any(UserCredentialsDto.class))).thenReturn(expectedToken);

        mockMvc.perform(post("/auth/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(credentials)))
                .andExpect(status().isOk());
        verify(userService).signIn(credentials);
    }
}
