package win.those.aegiru.chirper.post.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.post.dto.GetPostResponse;
import win.those.aegiru.chirper.post.entity.Post;

import java.util.function.Function;

@Component
public class PostToResponseFunction implements Function<Post, GetPostResponse> {
    @Override
    public GetPostResponse apply(Post post) {
        return GetPostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .build();
    }
}
