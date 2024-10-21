package win.those.aegiru.chirper.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import win.those.aegiru.chirper.user.controller.api.UserController;
import win.those.aegiru.chirper.user.dto.GetUserResponse;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;
import win.those.aegiru.chirper.user.dto.PutUserRequest;
import win.those.aegiru.chirper.user.function.RequestToUserFunction;
import win.those.aegiru.chirper.user.function.UserToResponseFunction;
import win.those.aegiru.chirper.user.function.UsersToResponseFunction;
import win.those.aegiru.chirper.user.service.api.UserService;
import win.those.aegiru.chirper.user.entity.User;

import java.io.IOException;
import java.util.UUID;

@Controller
public class UserDefaultController implements UserController {
    private final UserService service;

    private final UserToResponseFunction userToResponseFunction;

    private final UsersToResponseFunction usersToResponseFunction;

    private final RequestToUserFunction requestToUserFunction;

    @Autowired
    public UserDefaultController(UserService service, UserToResponseFunction userToResponseFunction, UsersToResponseFunction usersToResponseFunction, RequestToUserFunction requestToUserFunction) {
        this.service = service;
        this.userToResponseFunction = userToResponseFunction;
        this.usersToResponseFunction = usersToResponseFunction;
        this.requestToUserFunction = requestToUserFunction;
    }

    @Override
    public GetUsersResponse getUsers() {
        return usersToResponseFunction.apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID userId) {
        return service.find(userId)
                .map(userToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public byte[] getProfilePicture(UUID userId) {
        return service.find(userId)
                .map(User::getProfilePicture)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        service.create(requestToUserFunction.apply(id, request));
    }

    @Override
    public void putUserProfilePicture(UUID userId, MultipartFile request) {
        service.find(userId).ifPresentOrElse(
                user -> {
                    try {
                        service.updateProfilePicture(userId, request.getInputStream());
                    } catch (IOException e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                },
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
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
