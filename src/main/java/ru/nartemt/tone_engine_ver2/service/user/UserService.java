package ru.nartemt.tone_engine_ver2.service.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nartemt.tone_engine_ver2.model.entity.user.User;
import ru.nartemt.tone_engine_ver2.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(User user) {
        repository.save(user);
    }
}
