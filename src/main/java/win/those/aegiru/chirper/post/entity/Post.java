package win.those.aegiru.chirper.post.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import win.those.aegiru.chirper.user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Builder.Default
    @Id
    UUID id = UUID.randomUUID();
    String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    User author;
}
