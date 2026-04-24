package ru.nartemt.tone_engine_ver2.service.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.UserMapper;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtAuthenticationDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.JwtRefreshDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserCredentialsDto;
import ru.nartemt.tone_engine_ver2.model.dto.jwt.UserDto;
import ru.nartemt.tone_engine_ver2.model.entity.user.User;
import ru.nartemt.tone_engine_ver2.model.entity.user.UserRole;
import ru.nartemt.tone_engine_ver2.model.request.RegistrationRequest;
import ru.nartemt.tone_engine_ver2.repository.UserRepository;
import ru.nartemt.tone_engine_ver2.security.jwt.JwtService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    private void save(UserDto userDto) throws BadCredentialsException {
        if (userDto.password() == null || userDto.password().isBlank())
            throw new BadCredentialsException("Incorrect password");

        User user = mapper.toEntity(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.password());
        user.setPassword(encodedPassword);
        repository.save(user);
    }

    private User findByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private User findByCredentials(UserCredentialsDto credentialsDto) throws UsernameNotFoundException {
        User user = findByUsername(credentialsDto.name());
        if (passwordEncoder.matches(credentialsDto.password(), user.getPassword()))
            return user;
        else
            throw new UsernameNotFoundException("Incorrect password");
    }


    public JwtAuthenticationDto signIn(UserCredentialsDto credentialsDto) throws UsernameNotFoundException {
        User user = findByCredentials(credentialsDto);
        return jwtService.getJwtAuthentication(user.getName());
    }

    @Transactional
    public JwtAuthenticationDto signUp(RegistrationRequest request) throws BadCredentialsException {
        repository.findByName(request.name())
                .ifPresent(user -> {
                    throw new BadCredentialsException("This username is already exists");
                });

        UserDto userDto = UserDto.builder()
                .name(request.name())
                .password(request.password())
                .role(request.role())
                .build();
        save(userDto);
        return jwtService.getJwtAuthentication(request.name());
    }

    public JwtAuthenticationDto refresh(JwtRefreshDto refreshDto) throws InsufficientAuthenticationException {
        String refreshToken = refreshDto.refreshToken();
        if (refreshToken != null && jwtService.validateToken(refreshToken)) {
            User user = findByUsername(jwtService.getEmailFromToken(refreshToken));
            return jwtService.refresh(user.getName(), refreshToken);
        }
        throw new InsufficientAuthenticationException("Invalid refresh token");
    }

}
