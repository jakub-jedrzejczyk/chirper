package win.those.aegiru.chirper.user.controller.api;

import win.those.aegiru.chirper.user.dto.GetUserResponse;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;

import java.util.Optional;
import java.util.UUID;

public interface UserController {
    GetUsersResponse getUsers();

    GetUserResponse getUser(UUID userId);
}
