package review.steam_game.repository.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Comment;
import review.steam_game.entity.User;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long Id);

    Optional<Comment> findByUserid(String userid);
}
