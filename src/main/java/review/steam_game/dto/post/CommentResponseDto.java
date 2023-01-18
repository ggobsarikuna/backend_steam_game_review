package review.steam_game.dto.post;

import lombok.Getter;
import lombok.Setter;
import review.steam_game.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comments) {
        this.id = comments.getId();
        this.comment = comments.getComment();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
    }
}
