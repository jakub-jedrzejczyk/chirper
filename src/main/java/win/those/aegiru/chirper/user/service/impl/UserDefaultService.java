package win.those.aegiru.chirper.user.service.impl;

import org.springframework.stereotype.Service;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.repository.api.UserRepository;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
    public void updateProfilePicture(UUID id, byte[] profilePicture) {
        User user = userRepository.findById(id).orElseThrow();
        user.setProfilePicture(profilePicture);
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
