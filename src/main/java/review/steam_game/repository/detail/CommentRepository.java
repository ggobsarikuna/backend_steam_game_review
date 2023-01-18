package review.steam_game.repository.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import review.steam_game.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long Id);

//    List<Comment> findByPost_Id();

    List<Comment> findByPost_Id(Long Id);

//    List<Comment>
}
