package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany
    @JoinTable(name="watchlist_mediaElement",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "mediaElement_id"))
    private List<MediaElement> mediaElementList; //many to many
}
