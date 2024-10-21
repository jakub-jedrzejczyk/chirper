package win.those.aegiru.chirper.post.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.post.dto.PutPostRequest;
import win.those.aegiru.chirper.post.entity.Post;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToPostFunction implements BiFunction<UUID, PutPostRequest, Post> {
    @Override
    public Post apply(UUID id, PutPostRequest putPostRequest) {
        return Post.builder()
                .id(id)
                .content(putPostRequest.getContent())
                .build();
    }
}
