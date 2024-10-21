package win.those.aegiru.chirper.post.controller.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import win.those.aegiru.chirper.post.controller.api.PostController;
import win.those.aegiru.chirper.post.dto.GetPostResponse;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.dto.PutPostRequest;
import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.post.function.PostToResponseFunction;
import win.those.aegiru.chirper.post.function.PostsToResponseFunction;
import win.those.aegiru.chirper.post.function.RequestToPostFunction;
import win.those.aegiru.chirper.post.service.api.PostService;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@Log
public class PostDefaultController implements PostController {
    private final PostService service;

    private final UserService userService;

    private final PostToResponseFunction postToResponseFunction;

    private final PostsToResponseFunction postsToResponseFunction;

    private final RequestToPostFunction requestToPostFunction;

    @Autowired
    public PostDefaultController(
            PostService service,
            PostToResponseFunction postToResponseFunction,
            PostsToResponseFunction postsToResponseFunction,
            RequestToPostFunction requestToPostFunction,
            UserService userService
    ) {
        this.service = service;
        this.postToResponseFunction = postToResponseFunction;
        this.postsToResponseFunction = postsToResponseFunction;
        this.requestToPostFunction = requestToPostFunction;
        this.userService = userService;
    }

    @Override
    public GetPostsResponse getPosts() {
        return postsToResponseFunction.apply(service.findAll());
    }

    @Override
    public GetPostsResponse getUserPosts(UUID userId) {
        return service.findAllBy(userId)
                .map(postsToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetPostResponse getPost(UUID id) {
        return service.find(id)
                .map(postToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetPostResponse getUserPost(UUID userId, UUID postId) {
        return null;
    }

    @Override
    public void putPost(UUID id, UUID userId, PutPostRequest request) {
        Post post = requestToPostFunction.apply(id, request);
        Optional<User> author = userService.find(userId);
        System.out.println(author.toString());
        if (author.isPresent()) {
            post.setAuthor(author.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (service.find(id).isPresent()) {
            service.update(post);
        } else {
            service.create(post);
        }
    }

    @Override
    public void deletePost(UUID id) {
        service.find(id).ifPresentOrElse(
                post -> {
                    service.delete(id);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );

    }
}
