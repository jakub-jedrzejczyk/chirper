package win.those.aegiru.chirper.user.function;

import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;
import win.those.aegiru.chirper.user.entity.User;

import java.util.List;
import java.util.function.Function;

@Component
public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {
    @Override
    public GetUsersResponse apply(List<User> users) {
        return GetUsersResponse.builder()
                .users(users.stream().map(user -> GetUsersResponse.User.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .build())
                        .toList()).build();
    }
}
