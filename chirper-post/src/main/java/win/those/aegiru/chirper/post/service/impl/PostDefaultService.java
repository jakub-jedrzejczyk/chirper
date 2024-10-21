package win.those.aegiru.chirper.post.service.impl;

import org.springframework.stereotype.Service;
import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.post.repository.api.PostRepository;
import win.those.aegiru.chirper.post.service.api.PostService;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostDefaultService implements PostService {
    private final PostRepository postRepository;

    private final UserRepository userRepository;

    public PostDefaultService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Post> find(UUID id) {
        return postRepository.findById(id);
    }

    @Override
    public void create(Post post) {
        postRepository.save(post);
    }

    @Override
    public void update(Post post) {
        //post.setAuthor(postRepository.findById(post.getId()).get().getAuthor());
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAll(User user) {
        return postRepository.findAllByAuthor(user);
    }

    @Override
    public Optional<List<Post>> findAllBy(UUID id) {
        return userRepository.findById(id).map(postRepository::findAllByAuthor);
    }

    @Override
    public void delete(UUID id) {
        postRepository.deleteById(id);
    }
}
