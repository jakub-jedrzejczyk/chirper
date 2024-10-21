package win.those.aegiru.chirper;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostDTO implements Comparable<PostDTO> {
    UUID id;
    String content;
    String author;

    @Override
    public int compareTo(PostDTO postDTO) {
        return this.id.compareTo(postDTO.id);
    }
}
