package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "media_elements")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "media_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor

public class MediaElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String title;
    private String tagline;
    private String posterPath;
    @ManyToMany
    @JoinTable(name = "mediaElement_review",
    joinColumns = @JoinColumn(name = "mediaElement_id"), inverseJoinColumns =@JoinColumn(name="review_id") )
    private List<Review>reviewList;

    @ManyToMany
    @JoinTable(name = "mediaElement_genre", joinColumns = @JoinColumn(name= "mediaElement_id"), inverseJoinColumns = @JoinColumn(name ="genre_id" ))
    private List<Genre> genreList;

    @ManyToMany
    @JoinTable(name = "mediaElement_keyword", joinColumns = @JoinColumn(name = "mediaElement_id"), inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private List <Keyword> tagsList;

    @ManyToMany(mappedBy = "mediaElementList")
    private List<WatchList> watchListList;

}
