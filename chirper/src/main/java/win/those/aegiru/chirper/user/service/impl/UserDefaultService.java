package win.those.aegiru.chirper.user.service.impl;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RestController;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.repository.api.UserRepository;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Log
public class UserDefaultService implements UserService {
    private final UserRepository userRepository;

    public UserDefaultService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> find(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateProfilePicture(UUID id, InputStream profilePicture) {
        userRepository.findById(id).ifPresent(user -> {
            try {
                user.setProfilePicture(profilePicture.readAllBytes());
                userRepository.save(user);
            } catch (Exception e) {
                log.warning("Failed to update profile picture");
            }
        });
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
