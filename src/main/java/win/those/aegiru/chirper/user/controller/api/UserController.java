package win.those.aegiru.chirper.user.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import win.those.aegiru.chirper.user.dto.GetUserResponse;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;
import win.those.aegiru.chirper.user.dto.PutUserRequest;

import java.util.UUID;

public interface UserController {
    @GetMapping("api/users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetUsersResponse getUsers();

    @GetMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetUserResponse getUser(@PathVariable("userId") UUID userId);

    @GetMapping(path = "api/users/{userId}/profilePicture", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    byte[] getProfilePicture(@PathVariable("userId") UUID userId);

    @PutMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    void putUser(@PathVariable("userId") UUID userId, @RequestBody PutUserRequest request);

    @PutMapping("api/users/{userId}/profilePicture")
    @ResponseStatus(HttpStatus.CREATED)
    void putUserProfilePicture(@PathVariable("userId") UUID userId, @RequestParam("request") MultipartFile profilePicture);

    @DeleteMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable("userId") UUID userId);
}
