package win.those.aegiru.chirper.user.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import win.those.aegiru.chirper.dto.PutUserRequest;

import java.util.UUID;

public interface UserController {
    @PutMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void createUser(@PathVariable("userId") UUID userId);

    @DeleteMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable("userId") UUID userId);
}
