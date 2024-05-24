package elisa_daria.be_mood_watch.entities;

import elisa_daria.be_mood_watch.entities.enums.TvStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("TvShow")
@Getter
@Setter
@NoArgsConstructor

public class TvShow extends MediaElement{
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private int nOfSeasons;
    private LocalDate yearOfPilot;
    @Enumerated(EnumType.STRING)
    private TvStatus status;
}
