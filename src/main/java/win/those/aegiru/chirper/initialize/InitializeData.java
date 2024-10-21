package win.those.aegiru.chirper.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.post.entity.Post;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.post.service.api.PostService;
import win.those.aegiru.chirper.user.service.api.UserService;

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
        // Initialize data here

        User ElonMusk = User.builder()
                .username("elonmusk")
                .password("password")
                .displayName("Elon Musk")
                .build();

        User DonaldTrump = User.builder()
                .username("donaldtrump")
                .password("password")
                .displayName("Donald Trump")
                .build();

        User JoeBiden = User.builder()
                .username("joebiden")
                .password("password")
                .displayName("Joe Biden")
                .build();

        userService.create(ElonMusk);
        userService.create(DonaldTrump);
        userService.create(JoeBiden);

        Post post1 = Post.builder().content("I am the president of the United States.").author(DonaldTrump).build();
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
}
