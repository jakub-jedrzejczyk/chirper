package win.those.aegiru.chirper;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Post implements Serializable {
    @Builder.Default
    UUID id = UUID.randomUUID();
    String content;
    User author;
}
