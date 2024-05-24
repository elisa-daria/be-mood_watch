package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name="genres")
@Getter
@Setter
@NoArgsConstructor

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    @ManyToMany(mappedBy = "genreList")
    private List<MediaElement>mediaElementList;
}
