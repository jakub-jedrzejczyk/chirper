package win.those.aegiru.chirper;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {
    String username;

    @ToString.Exclude
    String password;

    String displayName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    List<Post> posts = new ArrayList<Post>();
}
