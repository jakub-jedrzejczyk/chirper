package win.those.aegiru.chirper.user.service.impl;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.event.repository.api.UserEventRepository;
import win.those.aegiru.chirper.user.repository.api.UserRepository;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDefaultService implements UserService {
    private final UserRepository userRepository;

    private final UserEventRepository eventRepository;

    public UserDefaultService(UserRepository userRepository, UserEventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<User> find(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
        eventRepository.create(user.getId());
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
        eventRepository.delete(id);
    }

    @Override
    public void updateProfilePicture(UUID id, InputStream profilePicture) {
        userRepository.findById(id).ifPresent(user -> {
            try {
                user.setProfilePicture(profilePicture.readAllBytes());
                userRepository.save(user);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
