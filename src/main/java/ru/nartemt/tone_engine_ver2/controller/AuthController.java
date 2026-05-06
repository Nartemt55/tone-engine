package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtRefreshDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserCredentialsDto;
import ru.nartemt.tone_engine_ver2.model.request.RegistrationRequest;
import ru.nartemt.tone_engine_ver2.service.user.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController")
@Slf4j
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "Войти в аккаунт",
            description = "Принимает UserCredentialsDto, если данные верны - вход успешен"
    )
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody @Valid UserCredentialsDto credentialsDto) {
        JwtAuthenticationDto jwtAuthenticationDto = userService.signIn(credentialsDto);
        log.info("Request for login : {}", credentialsDto.name());
        return ResponseEntity.ok(jwtAuthenticationDto);
    }

    @Operation(
            summary = "Обновить аутентификационный токен",
            description = "Принимает refresh, если он валиден то позволяет обновить аутентификационный токен"
    )
    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody JwtRefreshDto refreshDto) {
        log.info("Request for refresh token");
        return userService.refresh(refreshDto);
    }

    @Operation(
            summary = "Зарегистрироваться",
            description = "Принимает RegistrationRequest с данными для регистрации, если данные валидны - регистрация успешна"
    )
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationDto> signUp(@RequestBody @Valid RegistrationRequest request) {
        JwtAuthenticationDto jwtAuthenticationDto = userService.signUp(request);
        log.info("Request for sign up : {}", request.name());
        return ResponseEntity.ok(jwtAuthenticationDto);
    }
}
