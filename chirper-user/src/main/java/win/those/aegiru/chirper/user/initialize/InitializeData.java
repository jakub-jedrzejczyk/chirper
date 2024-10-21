package win.those.aegiru.chirper.user.initialize;

import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import win.those.aegiru.chirper.user.entity.User;
import win.those.aegiru.chirper.user.service.api.UserService;

import java.io.InputStream;
import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final UserService userService;

    @Autowired
    public InitializeData(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void afterPropertiesSet() {
        // Initialize data here

        User ElonMusk = User.builder()
                .id(UUID.fromString("b0a5b436-c704-4691-aa06-a0d624d48180"))
                .username("elonmusk")
                .password("password")
                .displayName("Elon Musk")
                .profilePicture(getResourceAsByteArray("../../avatar/musk.jpg"))
                .build();

        User DonaldTrump = User.builder()
                .id(UUID.fromString("1740c02e-3b0f-4e54-bf05-a092ab028092"))
                .username("donaldtrump")
                .password("password")
                .displayName("Donald Trump")
                .profilePicture(getResourceAsByteArray("../../avatar/trump.jpg"))
                .build();

        User JoeBiden = User.builder()
                .id(UUID.fromString("cbdf3ab7-5bad-43fc-83fc-0417f6652fac"))
                .username("joebiden")
                .password("password")
                .displayName("Joe Biden")
                .profilePicture(getResourceAsByteArray("../../avatar/biden.jpg"))
                .build();

        User BarackObama = User.builder()
                .id(UUID.fromString("f3b3b3b3-3b3b-3b3b-3b3b-3b3b3b3b3b3b"))
                .username("barackobama")
                .password("password")
                .displayName("Barack Obama")
                .profilePicture(getResourceAsByteArray("../../avatar/obama.jpg"))
                .build();

        userService.create(ElonMusk);
        userService.create(DonaldTrump);
        userService.create(JoeBiden);
        userService.create(BarackObama);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
