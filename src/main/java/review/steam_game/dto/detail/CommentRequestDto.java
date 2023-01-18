package review.steam_game.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import review.steam_game.entity.Comment;

import java.util.List;

@Data
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
