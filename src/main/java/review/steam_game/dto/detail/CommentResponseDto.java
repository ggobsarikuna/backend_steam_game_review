package review.steam_game.dto.detail;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import review.steam_game.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String comment;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
