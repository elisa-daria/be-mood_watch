package elisa_daria.be_mood_watch.repositories;

import elisa_daria.be_mood_watch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User>findById(long id);
}
