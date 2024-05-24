package elisa_daria.be_mood_watch.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import elisa_daria.be_mood_watch.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"reviews", "watchList"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String username;
    private String avatarURL;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List <WatchList> watchList;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User(String name, String surname, String email, String password, String username, String avatarURL, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.username = username;
        this.avatarURL = avatarURL;
        this.role = Role.USER;
    }
}
