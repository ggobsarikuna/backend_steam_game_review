package review.steam_game.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.entity.Post.Post;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long Id;


    @Column(nullable = false)
    private String comment;


    @Column(nullable = false)
    private Long apiId;

    //조회수
    @Column(nullable = false)
    private Long views;


    @ManyToOne
    @JoinColumn(name = "post_Id")
    private Post post;

    public Comment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}