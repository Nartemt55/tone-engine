package ru.nartemt.tone_engine_ver2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.nartemt.tone_engine_ver2.config.SecurityConfig;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtRefreshDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserCredentialsDto;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;
import ru.nartemt.tone_engine_ver2.model.request.RegistrationRequest;
import ru.nartemt.tone_engine_ver2.security.CustomUserService;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;
import ru.nartemt.tone_engine_ver2.service.user.UserService;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
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
    void signIn_ValidCredentials_ShouldReturnToken() throws Exception {

        UserCredentialsDto credentials = new UserCredentialsDto("admin", "admin");
        JwtAuthenticationDto expectedToken = new JwtAuthenticationDto("some.auth.token", "some.refresh.token");

        when(userService.signIn(any(UserCredentialsDto.class))).thenReturn(expectedToken);

        mockMvc.perform(post("/auth/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(credentials)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedToken.token()))
                .andExpect(jsonPath("$.refreshToken").value(expectedToken.refreshToken()));
        verify(userService).signIn(any(UserCredentialsDto.class));
    }

    @Test
    void signIn_WrongPassword_ShouldReturn403() throws Exception {
        UserCredentialsDto credentials = new UserCredentialsDto("admin", "admin");

        when(userService.signIn(any(UserCredentialsDto.class))).thenThrow(new BadCredentialsException("Неверный пароль"));

        mockMvc.perform(post("/auth/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(credentials)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").value("Неверный пароль"));
        verify(userService).signIn(any(UserCredentialsDto.class));
    }

    @Test
    void signIn_EmptyData_ShouldReturn400() throws Exception {
        UserCredentialsDto credentials = new UserCredentialsDto("", "");

        mockMvc.perform(post("/auth/sign-in")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(credentials)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Заполните поле имя"))
                .andExpect(jsonPath("$.password").value("Заполните поле пароль"));
        verifyNoInteractions(userService);
    }

    @Test
    void signUp_ValidData_ShouldReturnToken() throws Exception {
        RegistrationRequest request = new RegistrationRequest("MyName", "mypass", UserRole.USER);
        JwtAuthenticationDto expectedJwt = new JwtAuthenticationDto("some.auth.token", "some.refresh.token");

        when(userService.signUp(request)).thenReturn(expectedJwt);

        mockMvc.perform(post("/auth/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedJwt.token()))
                .andExpect(jsonPath("$.refreshToken").value(expectedJwt.refreshToken()));
        verify(userService).signUp(any(RegistrationRequest.class));
    }

    @Test
    void signUp_UsernameAlreadyExists_ShouldReturn403() throws Exception {
        RegistrationRequest request = new RegistrationRequest("MyName", "mypass", UserRole.USER);

        when(userService.signUp(request)).thenThrow(new BadCredentialsException("This username is already exists"));

        mockMvc.perform(post("/auth/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").value("This username is already exists"));
        verify(userService).signUp(any(RegistrationRequest.class));
    }

    @Test
    void signUp_EmptyPassword_ShouldReturn400() throws Exception {
        RegistrationRequest request = new RegistrationRequest("", "", UserRole.USER);

        mockMvc.perform(post("/auth/sign-up")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Заполните поле имя"))
                .andExpect(jsonPath("$.password").value("Заполните поле пароль"));
        verifyNoInteractions(userService);
    }

    @Test
    void refresh_ValidToken_ShouldReturnToken() throws Exception {
        JwtRefreshDto refresh = new JwtRefreshDto("some.refresh.token");
        JwtAuthenticationDto expectedToken = new JwtAuthenticationDto("some.auth.token", "some.refresh.token");

        when(userService.refresh(refresh)).thenReturn(expectedToken);

        mockMvc.perform(post("/auth/refresh")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(refresh)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedToken.token()))
                .andExpect(jsonPath("$.refreshToken").value(expectedToken.refreshToken()));
        verify(userService).refresh(any(JwtRefreshDto.class));
    }

    @Test
    void refresh_IncorrectToken_ShouldReturn403() throws Exception {
        JwtRefreshDto refresh = new JwtRefreshDto("some.refresh.token");

        when(userService.refresh(refresh)).thenThrow(new InsufficientAuthenticationException("Incorrect refresh token"));

        mockMvc.perform(post("/auth/refresh")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(refresh)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").value("Incorrect refresh token"));
        verify(userService).refresh(any(JwtRefreshDto.class));
    }
}
