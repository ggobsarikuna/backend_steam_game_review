package review.steam_game.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserid(String userid);
}
