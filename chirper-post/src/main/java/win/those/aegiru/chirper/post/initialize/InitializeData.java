package win.those.aegiru.chirper.post.initialize;

import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.post.service.api.PostService;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.io.InputStream;
import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public InitializeData(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {

        User ElonMusk = User.builder()
                .id(UUID.fromString("b0a5b436-c704-4691-aa06-a0d624d48180"))
                .build();

        User DonaldTrump = User.builder()
                .id(UUID.fromString("1740c02e-3b0f-4e54-bf05-a092ab028092"))
                .build();

        User JoeBiden = User.builder()
                .id(UUID.fromString("cbdf3ab7-5bad-43fc-83fc-0417f6652fac"))
                .build();

        User BarackObama = User.builder()
                .id(UUID.fromString("f3b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"))
                .build();

        userService.create(ElonMusk);
        userService.create(DonaldTrump);
        userService.create(JoeBiden);
        userService.create(BarackObama);

        Post post1 = Post.builder()
                .id(UUID.fromString("605733f0-7390-4136-81cc-abbc98be49ff"))
                .content("I am the president of the United States.").author(DonaldTrump).build();
        Post post2 = Post.builder().content("I am the president of the United States.").author(JoeBiden).build();
        Post post3 = Post.builder().content("I am the president of the United States.").author(ElonMusk).build();

        Post post4 = Post.builder().content("I am very rich.").author(DonaldTrump).build();
        Post post5 = Post.builder().content("I am very rich.").author(ElonMusk).build();

        Post post6 = Post.builder().content("I will win the presidential election.").author(DonaldTrump).build();
        Post post7 = Post.builder().content("I will not partake in the presidential election.").author(JoeBiden).build();

        Post post8 = Post.builder().content("I am the CEO of Tesla.").author(ElonMusk).build();
        Post post9 = Post.builder().content("I am the CEO of SpaceX.").author(ElonMusk).build();

        postService.create(post3);
        postService.create(post5);
        postService.create(post8);
        postService.create(post9);

        postService.create(post1);
        postService.create(post4);
        postService.create(post6);

        postService.create(post2);
        postService.create(post7);

        System.out.println("Data initialized.");
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
