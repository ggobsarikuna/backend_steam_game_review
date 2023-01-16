package review.steam_game.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
