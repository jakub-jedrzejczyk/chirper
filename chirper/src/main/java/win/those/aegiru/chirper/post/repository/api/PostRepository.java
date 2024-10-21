package win.those.aegiru.chirper.post.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.user.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByAuthor(User user);
}
