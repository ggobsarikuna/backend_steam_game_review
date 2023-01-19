package review.steam_game.dto.detail;

import lombok.*;
import review.steam_game.entity.Comment;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private String comment;
//    private String userid;
//    private Long postId;

//    List<String> comments;

//    private String test;

    public CommentRequestDto(Comment comment){
        this.comment = comment.getComment();
    }
}
