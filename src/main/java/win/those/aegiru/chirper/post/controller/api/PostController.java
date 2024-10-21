package win.those.aegiru.chirper.post.controller.api;

import win.those.aegiru.chirper.post.dto.GetPostResponse;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.dto.PutPostRequest;

import java.util.UUID;

public interface PostController {
    GetPostsResponse getPosts();

    GetPostsResponse getUserPosts(UUID userId);

    GetPostResponse getPost(UUID postId);

    GetPostResponse getUserPost(UUID userId, UUID postId);

    void putPost(UUID id, PutPostRequest request);

    void deletePost(UUID id);
}
