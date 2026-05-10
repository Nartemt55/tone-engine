package ru.nartemt.tone_engine_ver2.service.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.nartemt.tone_engine_ver2.mapper.UserMapper;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtRefreshDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserCredentialsDto;
import ru.nartemt.tone_engine_ver2.model.entity.user.User;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;
import ru.nartemt.tone_engine_ver2.model.request.RegistrationRequest;
import ru.nartemt.tone_engine_ver2.repository.UserRepository;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void signUp_UserAlreadyExists_ThrowsBadCredentialsException() {
        RegistrationRequest request = new RegistrationRequest("John Doe", " ", UserRole.USER);
        when(repository.findByName("John Doe")).thenReturn(Optional.of(new User()));

        assertThrows(BadCredentialsException.class, () -> {
            userService.signUp(request);
        });
    }

    @Test
    void signUp_EmptyPassword_ThrowsBadCredentialsException() {
        RegistrationRequest request = new RegistrationRequest("John Doe", " ", UserRole.USER);

        assertThrows(BadCredentialsException.class, () -> {
            userService.signUp(request);
        });
    }

    @Test
    void signUp_CorrectUser_SavesSuccessfully() {
        RegistrationRequest request = new RegistrationRequest("John Doe", "324", UserRole.USER);
        JwtAuthenticationDto expectedJwt = new JwtAuthenticationDto("fake.token.1", "fake.refresh.token");

        when(repository.findByName("John Doe")).thenReturn(Optional.empty());
        when(mapper.toEntity(any())).thenReturn(new User());
        when(jwtService.getJwtAuthentication(request.name())).thenReturn(expectedJwt);

        JwtAuthenticationDto result = userService.signUp(request);

        assertNotNull(result);
        assertEquals(expectedJwt, result);
        verify(repository).save(any(User.class));
    }

    @Test
    void singIn_IncorrectPassword_ThrowsBadCredentialsException() {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto("John Doe", "435");
        User expectedUser = new User(userCredentialsDto.name(), "145");

        when(repository.findByName(userCredentialsDto.name())).thenReturn(Optional.of(expectedUser));
        when(passwordEncoder.matches(userCredentialsDto.password(), expectedUser.getPassword())).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> {
           userService.signIn(userCredentialsDto);
        });
    }

    @Test
    void signIn_CorrectUserCredentials_Successfully() {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto("John Doe", "123");
        User expectedUser = new User(userCredentialsDto.name(), userCredentialsDto.password());
        JwtAuthenticationDto expectedJwt = new JwtAuthenticationDto("fake.token.1", "fake.refresh.token");

        when(repository.findByName(userCredentialsDto.name())).thenReturn(Optional.of(expectedUser));
        when(jwtService.getJwtAuthentication(userCredentialsDto.name())).thenReturn(expectedJwt);
        when(passwordEncoder.matches(userCredentialsDto.password(), expectedUser.getPassword())).thenReturn(true);
        JwtAuthenticationDto result = userService.signIn(userCredentialsDto);

        assertNotNull(result);
        assertEquals(expectedJwt, result);
    }

    @Test
    void signIn_UserNotExists_ThrowsUsernameNotFoundException() {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto("John Doe", "123");
        when(repository.findByName(userCredentialsDto.name())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
           userService.signIn(userCredentialsDto);
        });
    }

    @Test
    void refresh_IncorrectToken_ThrowsInsufficientAuthenticationException() {
        JwtRefreshDto refreshDto = new JwtRefreshDto("some.wrong.token");

        when(jwtService.validateToken(refreshDto.refreshToken())).thenReturn(false);

        assertThrows(InsufficientAuthenticationException.class, () -> {
            userService.refresh(refreshDto);
        });
    }

    @Test
    void refresh_CorrectToken_Successfully() {
        JwtRefreshDto refreshDto = new JwtRefreshDto("some.correct.token");
        JwtAuthenticationDto newJwt = new JwtAuthenticationDto("a.token.1", "r.token.1");
        String expectedUsername = "John Doe";
        User expectedUser = new User(expectedUsername, "123");


        when(jwtService.validateToken(refreshDto.refreshToken())).thenReturn(true);
        when(repository.findByName(expectedUsername)).thenReturn(Optional.of(expectedUser));
        when(jwtService.getUsernameFromToken(refreshDto.refreshToken())).thenReturn(expectedUsername);
        when(jwtService.refresh(expectedUsername, refreshDto.refreshToken())).thenReturn(newJwt);

        JwtAuthenticationDto result = userService.refresh(refreshDto);

        assertNotNull(result);
        assertEquals(result, newJwt);
    }

}
