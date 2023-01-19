package review.steam_game.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
