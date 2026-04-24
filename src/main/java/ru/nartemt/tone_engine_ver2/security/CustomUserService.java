package ru.nartemt.tone_engine_ver2.security;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.mapper.UserMapper;
import ru.nartemt.tone_engine_ver2.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return mapper.toCustomUserDetails(userRepository.findByName(username)
                .orElseThrow(EntityNotFoundException::new));
    }
}
