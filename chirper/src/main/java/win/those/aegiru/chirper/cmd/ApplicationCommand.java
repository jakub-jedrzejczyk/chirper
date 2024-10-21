package win.those.aegiru.chirper.cmd;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.ansi.function.ImageToAnsiArtFunction;
import win.those.aegiru.chirper.post.controller.api.PostController;
import win.those.aegiru.chirper.post.dto.GetPostsResponse;
import win.those.aegiru.chirper.post.dto.PutPostRequest;
import win.those.aegiru.chirper.user.controller.api.UserController;
import win.those.aegiru.chirper.user.dto.GetUsersResponse;
import win.those.aegiru.chirper.user.entity.User;

import java.util.List;
import java.util.Scanner;

@Component
public class ApplicationCommand implements CommandLineRunner {
    private final ObjectWriter writer;

    private final PostController postController;

    private final UserController userController;

    private final ImageToAnsiArtFunction imageToAnsiArtFunction;

    @Autowired
    public ApplicationCommand(ObjectWriter writer, PostController postController, UserController userController, ImageToAnsiArtFunction imageToAnsiArtFunction) {
        this.writer = writer;
        this.postController = postController;
        this.userController = userController;
        this.imageToAnsiArtFunction = imageToAnsiArtFunction;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean running = true;
        while (running) {
            System.out.print("chirper> ");
            command = scanner.nextLine();
            switch (command) {
                case "quit" -> {
                    running = false;
                }
                case "help" -> {
                    System.out.println("Available commands:");
                    System.out.println("get_users - Get all users");
                    System.out.println("get_posts - Get all posts");
                    System.out.println("add_post - Add a post");
                    System.out.println("delete_post - Delete a post");
                    System.out.println("quit - Quit the application");
                    System.out.println("help - Display this help message");
                }
                case "get_users" -> {
                    userController.getUsers().getUsers().forEach(user -> {
                        try {
                            System.out.println(writer.writeValueAsString(user));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                case "get_posts" -> {
                    postController.getPosts().getPosts().forEach(post -> {
                        try {
                            System.out.println(writer.writeValueAsString(post));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                case "add_post" -> {
                    System.out.println("Enter the content of the post: ");
                    String content = scanner.nextLine();
                    System.out.println("Choose User to post as:");
                    List<GetUsersResponse.User> users = userController.getUsers().getUsers();
                    for (int i = 0; i < users.size(); i++) {
                        System.out.println(i + ": " + users.get(i).getUsername());
                    }
                    String userIndexStr = scanner.nextLine();
                    int userIndex = Integer.parseInt(userIndexStr);
                    if (userIndex >= 0 && userIndex < users.size()) {
                        PutPostRequest putPostRequest = PutPostRequest.builder()
                                .content(content)
                                .build();
                        postController.putPost(users.get(userIndex).getId(), putPostRequest);
                    } else {
                        System.out.println("Invalid user index.");
                    }
                }
                case "delete_post" -> {
                    List<GetPostsResponse.Post> posts = postController.getPosts().getPosts();
                    for (int i = 0; i < posts.size(); i++) {
                        System.out.println(i + ": " + posts.get(i).getContent());
                    }
                    String postIndexStr = scanner.nextLine();
                    int postIndex = Integer.parseInt(postIndexStr);
                    if (postIndex >= 0 && postIndex < posts.size()) {
                        postController.deletePost(posts.get(postIndex).getId());
                    } else {
                        System.out.println("Invalid post index.");
                    }
                }
            }
        }
    }
}
