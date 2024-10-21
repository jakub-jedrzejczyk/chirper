package win.those.aegiru.chirper.post.service.api;

import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Optional<Post> find(UUID id);

    List<Post> findAll();

    List<Post> findAll(User user);

    Optional<List<Post>> findAllBy(UUID id);

    void create(Post post);

    void delete(UUID id);
}
