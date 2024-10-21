package win.those.aegiru.chirper.post.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.entity.Post;

import java.util.List;
import java.util.function.Function;

@Component
public class PostsToResponseFunction implements Function<List<Post>, GetPostsResponse> {
    @Override
    public GetPostsResponse apply(List<Post> posts) {
        return GetPostsResponse.builder()
                .posts(posts.stream().map(post -> GetPostsResponse.Post.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .build())
                        .toList()).build();
    }
}
