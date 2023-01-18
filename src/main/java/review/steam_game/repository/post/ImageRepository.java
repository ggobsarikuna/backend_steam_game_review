package review.steam_game.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Post.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
