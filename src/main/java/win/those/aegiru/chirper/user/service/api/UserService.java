package win.those.aegiru.chirper.user.service.api;

import win.those.aegiru.chirper.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> find(UUID id);

    List<User> findAll();

    void create(User user);

    void update(User user);

    void delete(UUID id);

    void updateProfilePicture(UUID id, byte[] profilePicture);
}
