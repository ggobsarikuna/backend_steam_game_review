package review.steam_game.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.entity.Post.Post;
import review.steam_game.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String comment;


//    @Column(nullable = false)
//    private Long apiId;
//
//    //조회수
//    @Column(nullable = false)
//    private Long views;

//    @Column(nullable = false)
//    private String test;

//    @Column(nullable = false)
//    private Long postId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    public Comment(CommentRequestDto commentRequestDto){
//        this.comment = commentRequestDto.getComment();
//    }

    public Comment(Post post, CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
        this.post = post;
    }

    public Comment(Post post, CommentRequestDto commentRequestDto, User user) {
        this.post = post;
        this.comment = commentRequestDto.getComment();
        this.user = user;
    }


//    public Comment(String test, String comment){
//        this.comment = commentRequestDto.getComment();
//        this.test = commentRequestDto.getTest();
//    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}