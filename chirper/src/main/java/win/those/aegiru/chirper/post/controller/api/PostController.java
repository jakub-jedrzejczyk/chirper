package win.those.aegiru.chirper.post.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.those.aegiru.chirper.post.dto.GetPostResponse;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.dto.PutPostRequest;

import java.util.UUID;

public interface PostController {
    @GetMapping("api/posts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPostsResponse getPosts();

    @GetMapping("api/users/{userId}/posts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPostsResponse getUserPosts(@PathVariable("userId") UUID userId);

    @GetMapping("api/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPostResponse getPost(@PathVariable("postId") UUID postId);

    @GetMapping("api/users/{userId}/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetPostResponse getUserPost(@PathVariable("userId") UUID userId, @PathVariable("postId") UUID postId);

    @PutMapping("api/posts/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    void putPost(@PathVariable("postId") UUID id, @RequestBody PutPostRequest request);

    @DeleteMapping("api/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePost(@PathVariable("postId") UUID id);
}
