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
import win.those.aegiru.chirper.post.function.PostToResponseFunction;
import win.those.aegiru.chirper.post.function.PostsToResponseFunction;
import win.those.aegiru.chirper.post.function.RequestToPostFunction;
import win.those.aegiru.chirper.post.service.api.PostService;

import java.util.UUID;

@RestController
@Log
public class PostDefaultController implements PostController {
    private final PostService service;

    private final PostToResponseFunction postToResponseFunction;

    private final PostsToResponseFunction postsToResponseFunction;

    private final RequestToPostFunction requestToPostFunction;

    @Autowired
    public PostDefaultController(PostService service, PostToResponseFunction postToResponseFunction, PostsToResponseFunction postsToResponseFunction, RequestToPostFunction requestToPostFunction) {
        this.service = service;
        this.postToResponseFunction = postToResponseFunction;
        this.postsToResponseFunction = postsToResponseFunction;
        this.requestToPostFunction = requestToPostFunction;
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
    public void putPost(UUID id, PutPostRequest request) {
        service.create(requestToPostFunction.apply(id, request));
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
