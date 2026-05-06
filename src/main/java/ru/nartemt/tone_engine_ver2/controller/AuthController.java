package ru.nartemt.tone_engine_ver2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
public class AuthController {

    private final UserService userService;


    @Operation(
            summary = "Войти в аккаунт",
            description = "Принимает UserCredentialsDto, если данные верны - вход успешен"
    )
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody UserCredentialsDto credentialsDto) {
        JwtAuthenticationDto jwtAuthenticationDto = userService.signIn(credentialsDto);
        return ResponseEntity.ok(jwtAuthenticationDto);
    }

    @Operation(
            summary = "Обновить аутентификационный токен",
            description = "Принимает refresh, если он валиден то позволяет обновить аутентификационный токен"
    )
    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody JwtRefreshDto refreshDto) {
        return userService.refresh(refreshDto);
    }

    @Operation(
            summary = "Зарегистрироваться",
            description = "Принимает RegistrationRequest с данными для регистрации, если данные валидны - регистрация успешна"
    )
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationDto> signUp(@RequestBody RegistrationRequest request) {
        JwtAuthenticationDto jwtAuthenticationDto = userService.signUp(request);
        return ResponseEntity.ok(jwtAuthenticationDto);
    }
}
