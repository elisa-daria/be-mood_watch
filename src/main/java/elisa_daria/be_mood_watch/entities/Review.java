package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToMany(mappedBy = "reviewList")
    private List<MediaElement> mediaElementList;
}
