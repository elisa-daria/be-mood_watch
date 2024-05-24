package elisa_daria.be_mood_watch.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@DiscriminatorValue("Movie")
@Getter
@Setter
@NoArgsConstructor
public class Movie extends MediaElement {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private LocalDate yearOfRelease;
}
