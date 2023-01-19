package review.steam_game.dto.detail;

import lombok.Data;
import review.steam_game.entity.Comment;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String comment;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
