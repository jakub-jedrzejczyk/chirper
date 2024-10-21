package win.those.aegiru.chirper.user.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.user.dto.PutUserRequest;
import win.those.aegiru.chirper.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {
    @Override
    public User apply(UUID id, PutUserRequest putUserRequest) {
        return User.builder()
                .id(id)
                .username(putUserRequest.getUsername())
                .password(putUserRequest.getPassword())
                .displayName(putUserRequest.getDisplayName())
                .build();
    }

}
