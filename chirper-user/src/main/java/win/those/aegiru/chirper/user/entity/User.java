package win.those.aegiru.chirper.user.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Builder.Default
    @Id
    UUID id = UUID.randomUUID();

    @Column(name = "username")
    String username;

    @ToString.Exclude
    String password;

    @Column(name = "display_name")
    String displayName;

    @Column(name = "profile_picture")
    @Lob
    byte[] profilePicture;
}
