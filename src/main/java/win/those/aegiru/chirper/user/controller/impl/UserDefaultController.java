package win.those.aegiru.chirper.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import win.those.aegiru.chirper.user.controller.api.UserController;
import win.those.aegiru.chirper.user.dto.GetUserResponse;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;
import win.those.aegiru.chirper.user.function.UserToResponseFunction;
import win.those.aegiru.chirper.user.function.UsersToResponseFunction;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.util.Optional;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class UserDefaultController implements UserController {
    private final UserService service;

    private final UserToResponseFunction userToResponseFunction;

    private final UsersToResponseFunction usersToResponseFunction;

    @Autowired
    public UserDefaultController(UserService service, UserToResponseFunction userToResponseFunction, UsersToResponseFunction usersToResponseFunction) {
        this.service = service;
        this.userToResponseFunction = userToResponseFunction;
        this.usersToResponseFunction = usersToResponseFunction;
    }

    @Override
    public GetUsersResponse getUsers() {
        return usersToResponseFunction.apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID userId) {
        return service.find(userId)
                .map(userToResponseFunction)
                .orElseThrow(NoSuchElementException::new);
    }
}
