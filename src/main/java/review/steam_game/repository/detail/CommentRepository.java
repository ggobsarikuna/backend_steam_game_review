package review.steam_game.repository.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import review.steam_game.entity.Comment;
import review.steam_game.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long Id);

//    List<Comment> findByPost_Id();
    Optional<Comment> findByCommentId(Long commentId);

    Optional<Comment> findByIdAndUserId(@Param("commentId")Long commentId, @Param("userId")User user);

    List<Comment> findByPost_Id(Long Id);

//    List<Comment>
}
