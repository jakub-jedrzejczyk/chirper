package win.those.aegiru.chirper.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import win.those.aegiru.chirper.dto.PutUserRequest;
import win.those.aegiru.chirper.user.controller.api.UserController;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.util.Optional;
import java.util.UUID;

@Controller
public class UserDefaultController implements UserController {
    private final UserService service;

    @Autowired
    public UserDefaultController(UserService service) {
        this.service = service;
    }

    @Override
    public void createUser(UUID userId) {
        service.create(User.builder().id(userId).build());
    }

    @Override
    public void deleteUser(UUID userId) {
        service.find(userId).ifPresentOrElse(
                user -> {
                    service.delete(userId);
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
    }
}
