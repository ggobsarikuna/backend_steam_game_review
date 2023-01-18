package review.steam_game.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import review.steam_game.dto.detail.CommentRequestDto;

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
    @JoinColumn(name = "post_Id")
    private Post post;

//    public Comment(CommentRequestDto commentRequestDto){
//        this.comment = commentRequestDto.getComment();
//    }

    public Comment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
//        this.test = commentRequestDto.getTest();
    }



//    public Comment(String test, String comment){
//        this.comment = commentRequestDto.getComment();
//        this.test = commentRequestDto.getTest();
//    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

}
