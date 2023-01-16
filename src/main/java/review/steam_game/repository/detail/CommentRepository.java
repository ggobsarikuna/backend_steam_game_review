package review.steam_game.repository.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long Id);

}
