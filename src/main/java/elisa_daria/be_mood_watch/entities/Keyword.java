package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor

public class Keyword {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "tagsList")
    private List<MediaElement> mediaElementList;
}
