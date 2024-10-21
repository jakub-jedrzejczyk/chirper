package win.those.aegiru.chirper.user.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.user.dto.GetUserResponse;
import win.those.aegiru.chirper.user.entity.User;

import java.util.function.Function;

@Component
public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .password(user.getPassword())
                .build();
    }
}
