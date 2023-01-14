package review.steam_game.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import review.steam_game.dto.detail.CommentRequestDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}
