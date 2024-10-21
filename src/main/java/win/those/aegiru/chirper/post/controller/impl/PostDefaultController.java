package win.those.aegiru.chirper.post.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import win.those.aegiru.chirper.post.controller.api.PostController;
import win.those.aegiru.chirper.post.dto.GetPostResponse;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.dto.PutPostRequest;
import win.those.aegiru.chirper.post.function.PostToResponseFunction;
import win.those.aegiru.chirper.post.function.PostsToResponseFunction;
import win.those.aegiru.chirper.post.function.RequestToPostFunction;
import win.those.aegiru.chirper.post.service.api.PostService;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
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
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public GetPostResponse getPost(UUID id) {
        return service.find(id)
                .map(postToResponseFunction)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public GetPostResponse getUserPost(UUID userId, UUID postId) {
        return service.findAllBy(userId)
                .map(posts -> posts.stream()
                        .filter(post -> post.getId().equals(postId))
                        .findFirst()
                        .map(postToResponseFunction)
                        .orElseThrow(NoSuchElementException::new))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void putPost(UUID id, PutPostRequest request) {
        service.create(requestToPostFunction.apply(id, request));
    }

    @Override
    public void deletePost(UUID id) {
        service.delete(id);
    }
}
